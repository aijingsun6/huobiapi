package org.alking.huobiapi;

import org.alking.huobiapi.domain.HuobiKLineData;
import org.alking.huobiapi.domain.ws.HuobiWSError;
import org.alking.huobiapi.domain.ws.HuobiWSKLineEvent;
import org.alking.huobiapi.misc.HuobiWSEventHandler;
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

    @Test
    public void wsTest() throws HuobiApiException, InterruptedException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();

        client.kline("btcusdt", "5min", new HuobiWSEventHandler() {
            @Override
            public void handleKLine(HuobiWSKLineEvent event) {
                System.out.println(event);
            }

            @Override
            public void onError(HuobiWSError error) {
                System.err.println(error);
            }
        });

        Thread.sleep(1000 * 60);
    }

    @Test
    public void wsErrorTest() throws HuobiApiException, InterruptedException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();

        client.kline("btcusdt12345", "5min", new HuobiWSEventHandler() {
            @Override
            public void handleKLine(HuobiWSKLineEvent event) {
                System.out.println(event);
            }

            @Override
            public void onError(HuobiWSError error) {
                System.err.println(error);
            }
        });

        Thread.sleep(1000 * 60);
    }
}
