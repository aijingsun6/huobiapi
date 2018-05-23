package org.alking.huobiapi.domain.ws;

import org.alking.huobiapi.domain.HuobiTradeDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class HuobiWSTradeDetailEvent extends HuobiWSEvent {

    private List<HuobiTradeDetail> details;

    public List<HuobiTradeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<HuobiTradeDetail> details) {
        this.details = details;
    }

    public HuobiWSTradeDetailEvent() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ts",getTs())
                .append("details", getDetails())
                .toString();
    }
}
