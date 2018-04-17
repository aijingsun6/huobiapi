package org.alking.huobiapi;


import org.alking.huobiapi.domain.HuobiOrderBook;

public interface HuobiApiWSClient {

    HuobiOrderBook depth(String symbol, String type) throws HuobiApiException;
}
