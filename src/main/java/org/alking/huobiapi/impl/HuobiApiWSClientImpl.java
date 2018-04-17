package org.alking.huobiapi.impl;


import org.alking.huobiapi.HuobiApiException;
import org.alking.huobiapi.HuobiApiWSClient;
import org.alking.huobiapi.domain.HuobiOrderBook;

import java.util.concurrent.ConcurrentHashMap;

public class HuobiApiWSClientImpl implements HuobiApiWSClient {

    private static final ConcurrentHashMap<String, HuobiApiDepthCache> depthCacheMap = new ConcurrentHashMap<>();

    private static final Object depthCacheLock = new Object();

    @Override
    public HuobiOrderBook depth(String symbol, String type) throws HuobiApiException {
        synchronized (depthCacheLock) {
            if (!depthCacheMap.containsKey(symbol)) {
                HuobiApiDepthCache cache = new HuobiApiDepthCache(symbol, type);
                depthCacheMap.put(symbol, cache);
            }

        }
        HuobiApiDepthCache cache = depthCacheMap.get(symbol);
        return cache.getCache();
    }
}
