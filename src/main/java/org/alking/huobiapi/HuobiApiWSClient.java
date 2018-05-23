package org.alking.huobiapi;

import org.alking.huobiapi.misc.HuobiWSEventHandler;

public interface HuobiApiWSClient {

    void depth(String symbol, String type, HuobiWSEventHandler handler) throws HuobiApiException;
}
