package org.alking.huobiapi;

import org.alking.huobiapi.domain.HuobiTick;
import org.junit.Assert;
import org.junit.Test;

public class HuobiTickTest {
    @Test
    public void test() throws HuobiApiException {

        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();

        HuobiTick tick = client.tick("btcusdt");

        Assert.assertNotNull(tick);

    }
}
