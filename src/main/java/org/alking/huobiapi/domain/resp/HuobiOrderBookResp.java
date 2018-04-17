package org.alking.huobiapi.domain.resp;

import org.alking.huobiapi.domain.HuobiOrderBook;
import org.alking.huobiapi.domain.HuobiOrderBookEntry;

import java.util.ArrayList;
import java.util.List;

public class HuobiOrderBookResp extends HuobiResp {

    public static class HuobiOrderBookTick {

        public long ts;

        public List<List<Double>> bids;

        public List<List<Double>> asks;

    }

    public HuobiOrderBookTick tick;

    public HuobiOrderBookResp() {
    }

    private List<HuobiOrderBookEntry> parseEntries(List<List<Double>> list){
        List<HuobiOrderBookEntry> result = new ArrayList<>();
        for(List<Double> e: list){
            result.add( new HuobiOrderBookEntry(e.get(0),e.get(1)));
        }
        return result;
    }

    public HuobiOrderBook toOrderBook(){
        HuobiOrderBook result = new HuobiOrderBook();
        if(tick != null){
            result.setTs( tick.ts );
            result.setAsks( parseEntries(tick.asks) );
            result.setBids( parseEntries(tick.bids) );
        }
        return result;
    }
}
