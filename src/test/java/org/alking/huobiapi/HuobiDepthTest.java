package org.alking.huobiapi;


import org.alking.huobiapi.domain.HuobiOrderBook;
import org.alking.huobiapi.domain.ws.HuobiWSDepthEvent;
import org.alking.huobiapi.misc.HuobiWSEventHandler;
import org.junit.Assert;
import org.junit.Test;

public class HuobiDepthTest {

    @Test
    public void wsTest() throws HuobiApiException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();
        client.depth("eosbtc", "step0", new HuobiWSEventHandler() {
            @Override
            public void handleDepth(HuobiWSDepthEvent event) {
                System.out.println(event.toString());
            }
        });

        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void restTest() throws HuobiApiException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        HuobiOrderBook ob = client.depth("eosbtc", "step0");
        Assert.assertNotNull( ob );
        System.out.println(ob);
    }

}
