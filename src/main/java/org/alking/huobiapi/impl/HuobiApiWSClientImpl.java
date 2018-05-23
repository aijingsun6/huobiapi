package org.alking.huobiapi.impl;

import okhttp3.*;
import org.alking.huobiapi.HuobiApiException;
import org.alking.huobiapi.HuobiApiWSClient;
import org.alking.huobiapi.impl.ws.HuobiApiWSDepthClient;
import org.alking.huobiapi.misc.HuobiWSEventHandler;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;
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
        new HuobiApiWSDepthClient(symbol,type,handler,this.client).start();
    }

    @Override
    public void close() throws IOException {
        this.client.dispatcher().executorService().shutdown();
    }
}
