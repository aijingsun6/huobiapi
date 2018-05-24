package org.alking.huobiapi;

import org.alking.huobiapi.domain.ws.HuobiWSMarketDetailEvent;
import org.alking.huobiapi.misc.HuobiWSEventHandler;
import org.junit.Test;

public class HuobiMarketDetailTest {

    @Test
    public void wsTest() throws HuobiApiException, InterruptedException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();

        client.marketDetail("btcusdt",  new HuobiWSEventHandler() {

            @Override
            public void handleMarketDetail(HuobiWSMarketDetailEvent event) {
                System.out.println(event);
            }
        });

        Thread.sleep(1000 * 60);
    }
}
