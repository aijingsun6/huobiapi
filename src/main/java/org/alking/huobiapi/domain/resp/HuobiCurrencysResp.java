package org.alking.huobiapi.domain.resp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HuobiCurrencysResp extends HuobiResp {

    @SerializedName("data")
    private List<String> symbols = new ArrayList<>();

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public HuobiCurrencysResp() {
    }
}
