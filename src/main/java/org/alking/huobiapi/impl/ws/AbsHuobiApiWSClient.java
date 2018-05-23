package org.alking.huobiapi.impl.ws;

import okhttp3.*;
import okio.ByteString;
import org.alking.huobiapi.constant.HuobiConst;
import org.alking.huobiapi.domain.resp.HuobiWSResp;
import org.alking.huobiapi.domain.ws.HuobiWSError;
import org.alking.huobiapi.domain.ws.HuobiWSSub;
import org.alking.huobiapi.misc.HuobiWSEventHandler;
import org.alking.huobiapi.util.HuobiUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbsHuobiApiWSClient<T extends HuobiWSResp> extends WebSocketListener implements Closeable {

    protected final OkHttpClient client;

    protected final HuobiWSEventHandler handler;

    protected final Class<T> clazz;

    protected WebSocket webSocket;

    public AbsHuobiApiWSClient(final OkHttpClient client, final HuobiWSEventHandler handler, final Class<T> clazz) {
        this.client = client;
        this.handler = handler;
        this.clazz = clazz;
    }

    public void start() {
        Request.Builder builder = new Request.Builder().url(HuobiConst.WS_URL);
        this.webSocket = client.newWebSocket(builder.build(), this);
    }

    public void shutdown() {
        this.webSocket.close(0, "manual");
    }

    protected abstract HuobiWSSub calcSub();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println(String.format("%s onOpen", getClass().getSimpleName()));
        // String id = UUID.randomUUID().toString();
        // HuobiWSSub sub = new HuobiWSSub(String.format("market.%s.depth.%s", symbol, type), id);
        HuobiWSSub sub = calcSub();
        this.webSocket.send(HuobiUtil.toJson(sub));
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        // logger.info("onMessage {},{}", symbol,text);
    }

    protected abstract void doHandler(T resp);

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

        if (json.contains("ping")) {
            String pong = json.replace("ping", "pong");
            webSocket.send(pong);
            return;
        }
        // System.out.println(json);
        T resp = HuobiUtil.fromJson(json, this.clazz);
        if (resp.status != null && !resp.status.equals(HuobiWSResp.STATUES_OK)) {
            HuobiWSError err = new HuobiWSError(resp.errCode, resp.errMsg);
            if (handler != null) {
                this.handler.onError(err);
            }
        } else {
            this.doHandler(resp);
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        System.out.println(String.format("%s onClosing %d,%s", getClass().getSimpleName(), code, reason));
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        System.out.println(String.format("%s onClosed %d,%s", getClass().getSimpleName(), code, reason));
        if (this.handler != null) {
            this.handler.onClosed(code, reason);
        }

    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println(String.format("%s onFailure,%s", getClass().getSimpleName(), t.getMessage()));
        if (this.handler != null) {
            handler.onFailure(t.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        this.shutdown();
    }
}
