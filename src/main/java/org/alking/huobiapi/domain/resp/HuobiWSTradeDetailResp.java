package org.alking.huobiapi.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.alking.huobiapi.domain.HuobiTradeDetail;

import java.util.List;

@JsonIgnoreProperties
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
