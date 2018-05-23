package org.alking.huobiapi.domain.ws;

import org.alking.huobiapi.domain.HuobiKLineData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class HuobiWSKLineEvent extends HuobiWSEvent {

    private HuobiKLineData data;

    public HuobiKLineData getData() {
        return data;
    }

    public void setData(HuobiKLineData data) {
        this.data = data;
    }

    public HuobiWSKLineEvent() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ts", getTs())
                .append("date", getData())
                .toString();
    }
}
