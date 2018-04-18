package org.alking.huobiapi.domain.resp;

import org.alking.huobiapi.domain.HuobiTick;

public class HuobiTickResp extends HuobiResp {

    private HuobiTick tick;

    public HuobiTick getTick() {
        return tick;
    }

    public void setTick(HuobiTick tick) {
        this.tick = tick;
    }

    public HuobiTickResp() {
    }
}
