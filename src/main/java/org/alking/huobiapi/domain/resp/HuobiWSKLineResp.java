package org.alking.huobiapi.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.alking.huobiapi.domain.HuobiKLineData;

@JsonIgnoreProperties
public class HuobiWSKLineResp extends HuobiWSResp{

    public HuobiKLineData tick;

    public HuobiWSKLineResp() {
    }
}
