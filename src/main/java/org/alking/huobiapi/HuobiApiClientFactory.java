package org.alking.huobiapi;

import org.alking.huobiapi.impl.HuobiApiRestClientImpl;
import org.alking.huobiapi.impl.HuobiApiWSClientImpl;

public class HuobiApiClientFactory {

    private String apiKey = null;

    private String secret = null;

    public HuobiApiClientFactory() {
    }

    private HuobiApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    public static HuobiApiClientFactory newInstance(){
        return new HuobiApiClientFactory();
    }

    public static HuobiApiClientFactory newInstance(String apiKey, String secret){
        return new HuobiApiClientFactory(apiKey, secret);
    }

    public HuobiApiRestClient newRestClient(){
        return new HuobiApiRestClientImpl(apiKey, secret);
    }
    public HuobiApiWSClient newWSClient(){
        return new HuobiApiWSClientImpl();
    }

}
