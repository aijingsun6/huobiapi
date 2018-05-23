package org.alking.huobiapi.domain.resp;

import com.google.gson.annotations.SerializedName;

public class HuobiWSResp {

    public static final String STATUES_OK = "ok";

    public String ch;

    public long ts;

    public String id;

    public String status;

    @SerializedName("err-code")
    public String errCode;


    @SerializedName("err-msg")
    public String errMsg;
}
