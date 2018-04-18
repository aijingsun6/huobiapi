package org.alking.huobiapi;


import org.alking.huobiapi.domain.HuobiOrderBook;
import org.junit.Assert;
import org.junit.Test;

public class HuobiDepthTest {


    @Test
    public void wsTest() throws InterruptedException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();
        HuobiOrderBook ob;
        for (int i = 0 ; i < 60; i ++){
            try {
                ob = client.depth("eosbtc", "step0");
                System.out.println(ob);

            } catch (HuobiApiException e) {

            }
            Thread.sleep(1000);
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
