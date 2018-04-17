package org.alking.huobiapi;


import org.alking.huobiapi.domain.HuobiOrderBook;
import org.junit.Test;

public class HuobiDepthTest {


    @Test
    public void test() throws InterruptedException {

        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();

        HuobiOrderBook ob;

        for (int i = 0 ; i < 100; i ++){
            try {
                ob = client.depth("eosbtc", "step0");
                System.out.println(ob);

                // ob = client.depth("xrpbtc", "step0");
                // System.out.println(ob);

            } catch (HuobiApiException e) {

            }
            Thread.sleep(1000);
        }
    }

}
