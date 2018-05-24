package org.alking.huobiapi.impl;

import okhttp3.OkHttpClient;
import org.alking.huobiapi.HuobiApiException;
import org.alking.huobiapi.HuobiApiWSClient;
import org.alking.huobiapi.impl.ws.HuobiApiWSDepthClient;
import org.alking.huobiapi.impl.ws.HuobiApiWSKLineClient;
import org.alking.huobiapi.impl.ws.HuobiApiWSMarketDetailClient;
import org.alking.huobiapi.impl.ws.HuobiApiWSTradeDetailClient;
import org.alking.huobiapi.misc.HuobiWSEventHandler;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HuobiApiWSClientImpl implements HuobiApiWSClient, Closeable {

    private static final OkHttpClient.Builder build = new OkHttpClient.Builder();

    static {
        build.connectTimeout(10, TimeUnit.SECONDS);
        build.readTimeout(10, TimeUnit.SECONDS);
        build.writeTimeout(10, TimeUnit.SECONDS);
    }

    private OkHttpClient client;

    public HuobiApiWSClientImpl() {
        this.client = build.build();
        //TODO:可配置
        this.client.dispatcher().setMaxRequests(32);
        this.client.dispatcher().setMaxRequestsPerHost(32);
    }

    @Override
    public void depth(String symbol, String type, HuobiWSEventHandler handler) throws HuobiApiException {
        try {
            new HuobiApiWSDepthClient(this.client, handler, symbol, type).start();
        } catch (Exception e) {
            throw new HuobiApiException(e);
        }
    }

    @Override
    public void kline(String symbol, String period, HuobiWSEventHandler handler) throws HuobiApiException {

        try {
            new HuobiApiWSKLineClient(this.client, handler, symbol, period).start();
        } catch (Exception e) {
            throw new HuobiApiException(e);
        }

    }

    @Override
    public void tradeDetail(String symbol, HuobiWSEventHandler handler) throws HuobiApiException {
        try {
            new HuobiApiWSTradeDetailClient(this.client, handler, symbol).start();
        } catch (Exception e) {
            throw new HuobiApiException(e);
        }
    }

    @Override
    public void marketDetail(String symbol, HuobiWSEventHandler handler) throws HuobiApiException {
        try {
            new HuobiApiWSMarketDetailClient(this.client, handler, symbol).start();
        } catch (Exception e) {
            throw new HuobiApiException(e);
        }
    }

    @Override
    public void close() throws IOException {
        this.client.dispatcher().executorService().shutdown();
    }
}
