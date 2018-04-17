package org.alking.huobiapi;

import org.alking.huobiapi.domain.HuobiKLineData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HuobiKLineTest {

    @Test
    public void test() throws HuobiApiException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        List<HuobiKLineData> list = client.kline("btcusdt","5min",10);
        Assert.assertFalse(list.isEmpty());
        for (HuobiKLineData data: list){
            System.out.println(data);
        }
    }

    @Test(expected = HuobiApiException.class)
    public void testWithErrorSymbol() throws HuobiApiException{
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        client.kline("btcusdt123","5min",10);
    }
}
