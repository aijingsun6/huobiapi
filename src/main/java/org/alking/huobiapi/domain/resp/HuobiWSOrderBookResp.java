package org.alking.huobiapi.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.alking.huobiapi.domain.HuobiOrderBookEntry;

import java.util.List;

@JsonIgnoreProperties
public class HuobiWSOrderBookResp extends HuobiWSResp {

    @JsonIgnoreProperties
    public static class HuobiOrderBookTick {

        public List<HuobiOrderBookEntry> bids;

        public List<HuobiOrderBookEntry> asks;

        public long ts;

        public long version;

    }

    public HuobiOrderBookTick tick;

    public HuobiWSOrderBookResp() {

    }

}
