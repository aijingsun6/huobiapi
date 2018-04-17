package org.alking.huobiapi.domain.ws;


import com.google.gson.annotations.SerializedName;

public class HuobiWSResp {

    private long ts;

    private String status;

    @SerializedName("err-code")
    private String errCode;

    @SerializedName("err-msg")
    private String errMsg;

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public HuobiWSResp() {
    }
}
