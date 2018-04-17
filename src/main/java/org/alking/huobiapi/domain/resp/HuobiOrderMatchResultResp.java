package org.alking.huobiapi.domain.resp;


import com.google.gson.annotations.SerializedName;
import org.alking.huobiapi.domain.HuobiOrderMatchResult;

public class HuobiOrderMatchResultResp extends HuobiResp {

    @SerializedName("data")
    private HuobiOrderMatchResult matchResult;

    public HuobiOrderMatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(HuobiOrderMatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public HuobiOrderMatchResultResp() {
    }
}
