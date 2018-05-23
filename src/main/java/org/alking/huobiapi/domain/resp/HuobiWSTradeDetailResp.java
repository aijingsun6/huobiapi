package org.alking.huobiapi.domain.resp;

import org.alking.huobiapi.domain.HuobiTradeDetail;

import java.util.List;

public class HuobiWSTradeDetailResp extends HuobiWSResp {

    public static class HuobiWSTradeDetailTick {

        public long id;

        public long ts;

        public List<HuobiTradeDetail> data;

        public HuobiWSTradeDetailTick() {
        }
    }

    public HuobiWSTradeDetailTick tick;

    public HuobiWSTradeDetailResp() {
    }
}
