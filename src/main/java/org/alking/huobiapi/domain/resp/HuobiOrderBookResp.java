package org.alking.huobiapi.domain.resp;

import org.alking.huobiapi.domain.HuobiOrderBook;
import org.alking.huobiapi.domain.HuobiOrderBookEntry;
import org.alking.huobiapi.domain.ws.HuobiWSDepthEvent;

import java.util.ArrayList;
import java.util.List;

public class HuobiOrderBookResp extends HuobiResp {

    public static class HuobiOrderBookTick {

        public long ts;

        public List<List<Double>> bids;

        public List<List<Double>> asks;

    }

    public HuobiOrderBookTick tick;

    public boolean valid(){
        if(tick == null){
            return false;
        }
        return true;
    }

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
            result.setTs(  getTs() );
            result.setAsks( parseEntries(tick.asks) );
            result.setBids( parseEntries(tick.bids) );
        }
        return result;
    }

    public HuobiWSDepthEvent toDepthEvent(){
        HuobiWSDepthEvent event = new HuobiWSDepthEvent();
        event.setTs( getTs() );
        if(tick != null){
            event.setAsks(  parseEntries(tick.asks) );
            event.setBids(  parseEntries(tick.bids) );
        }
        return event;
    }
}
