package org.alking.huobiapi.impl;

import okhttp3.*;
import okio.ByteString;
import org.alking.huobiapi.constant.HuobiConst;
import org.alking.huobiapi.domain.HuobiOrderBook;
import org.alking.huobiapi.domain.HuobiOrderBookEntry;
import org.alking.huobiapi.domain.resp.HuobiOrderBookResp;
import org.alking.huobiapi.domain.ws.HuobiWSSub;
import org.alking.huobiapi.util.HuobiUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class HuobiApiDepthCache extends WebSocketListener implements Closeable {

    private static final Logger logger = LogManager.getLogger(HuobiApiDepthCache.class);

    private static final long TIMEOUT_MS = 1000 * 100;

    private static final OkHttpClient.Builder build = new OkHttpClient.Builder();

    private OkHttpClient client;

    static {
        build.connectTimeout(10, TimeUnit.SECONDS);
        build.readTimeout(10, TimeUnit.SECONDS);
        build.writeTimeout(10, TimeUnit.SECONDS);
    }

    private String symbol;

    private String type;

    private AtomicLong lastUpdate = new AtomicLong(0);

    private static final int ASKS_SIZE = 10;
    private final TreeMap<Double,Double> asks = new TreeMap<>();

    private static final int BIDS_SIZE = 10;
    private final TreeMap<Double,Double> bids = new TreeMap<>(Comparator.reverseOrder());

    private final Object lock = new Object();

    public HuobiOrderBook getCache() {
        long last = lastUpdate.get();
        if(last < 1){
            // not init
            HuobiOrderBook ob = new HuobiOrderBook();
            ob.setTs(last);
            return ob;
        }
        long now = System.currentTimeMillis();
        if(now - last > TIMEOUT_MS){
            logger.info("{} timeout", symbol);
            reset();
            synchronized (lock){
                // timeout
                HuobiOrderBook ob = new HuobiOrderBook();
                ob.setTs(lastUpdate.get());
                return ob;
            }

        }
        synchronized (lock){
            HuobiOrderBook ob = new HuobiOrderBook();
            ob.setTs(lastUpdate.get());
            ob.setAsks( map2ob(this.asks,ASKS_SIZE) );
            ob.setBids( map2ob(this.bids,BIDS_SIZE));
            return ob;
        }
    }

    private List<HuobiOrderBookEntry> map2ob(Map<Double, Double> map, int limit){
        int size = 0;
        List<HuobiOrderBookEntry> result = new ArrayList<>();
        for (Map.Entry<Double,Double> e: map.entrySet()){
            if(size < limit){
                HuobiOrderBookEntry entry = new HuobiOrderBookEntry(e.getKey(),e.getValue());
                result.add(entry);
            }
            size ++;
            if(size > limit){
                break;
            }
        }
        return result;
    }

    private String calcSub() {
        return String.format("market.%s.depth.%s", this.symbol, this.type);
    }

    public HuobiApiDepthCache(String symbol, String type) {
        this.symbol = symbol;
        this.type = type;
        this.reset();
    }

    private void reset() {
        client = build.build();
        client.dispatcher().setMaxRequests(512);
        client.dispatcher().setMaxRequestsPerHost(512);
        long now = System.currentTimeMillis();
        this.lastUpdate.set(now);
        this.asks.clear();
        this.bids.clear();
        Request.Builder builder = new Request.Builder().url(HuobiConst.WS_URL);
        client.newWebSocket(builder.build(), this);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        logger.info("{} onOpen", symbol);

        long now = System.currentTimeMillis();
        this.lastUpdate.set(now);

        String id = String.valueOf(now);
        HuobiWSSub sub = new HuobiWSSub();
        sub.setSub(calcSub());
        sub.setId(id);
        webSocket.send(HuobiUtil.toJson(sub));
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        // logger.info("onMessage {},{}", symbol,text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        String json = null;
        try {
            json = HuobiUtil.uncompress(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(json)) {
            return;
        }
        long now = System.currentTimeMillis();
        this.lastUpdate.set(now);

        if (json.contains("ping")) {
            // logger.info("ping-pong {}", symbol);
            String pong = json.replace("ping", "pong");
            webSocket.send(pong);
            return;
        }
        HuobiOrderBookResp resp = HuobiUtil.fromJson(json, HuobiOrderBookResp.class);
        if (resp != null && resp.tick != null) {
            // logger.info("depth data {}",symbol);
            parseData(resp.tick);
            return;
        }
        logger.warn("unknown message:{}", json);
    }


    private void parseData(HuobiOrderBookResp.HuobiOrderBookTick tick){
        synchronized (lock){
            this.asks.clear();
            for (List<Double> list: tick.asks){
                this.asks.put(list.get(0), list.get(1));
            }
            this.bids.clear();
            for (List<Double> list: tick.bids){
                this.bids.put(list.get(0), list.get(1));
            }
        }
    }


    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        logger.warn("onClosing {},{}", symbol, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        logger.error("onClosed {},{}", symbol, reason);
        reset();
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        logger.error("onFailure {}", symbol);
        reset();
    }

    @Override
    public void close() throws IOException {
        logger.info("close {}",symbol);
    }
}
