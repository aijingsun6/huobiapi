package org.alking.huobiapi.domain.ws;

import org.alking.huobiapi.domain.HuobiOrderBookEntry;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class HuobiWSDepthEvent extends HuobiWSEvent{

    private List<HuobiOrderBookEntry> bids = new ArrayList<>();

    private List<HuobiOrderBookEntry> asks = new ArrayList<>();

    public List<HuobiOrderBookEntry> getBids() {
        return bids;
    }

    public void setBids(List<HuobiOrderBookEntry> bids) {
        this.bids = bids;
    }

    public List<HuobiOrderBookEntry> getAsks() {
        return asks;
    }

    public void setAsks(List<HuobiOrderBookEntry> asks) {
        this.asks = asks;
    }

    public HuobiWSDepthEvent() {
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ts",getTs())
                .append("bids", getBids())
                .append("asks", getAsks())
                .toString();
    }
}
