package org.alking.huobiapi.domain.resp;

import java.util.List;

public class HuobiWSOrderBookResp extends HuobiWSResp {

    public static class HuobiOrderBookTick {

        public List<List<Double>> bids;

        public List<List<Double>> asks;

    }

    public HuobiOrderBookResp.HuobiOrderBookTick tick;

    public HuobiWSOrderBookResp() {
    }


}
