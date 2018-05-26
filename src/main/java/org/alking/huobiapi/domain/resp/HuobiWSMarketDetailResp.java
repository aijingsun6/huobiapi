package org.alking.huobiapi.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.alking.huobiapi.domain.HuobiMarketDetail;

@JsonIgnoreProperties
public class HuobiWSMarketDetailResp extends HuobiWSResp {

    public HuobiMarketDetail tick;

    public HuobiWSMarketDetailResp() {

    }
}
