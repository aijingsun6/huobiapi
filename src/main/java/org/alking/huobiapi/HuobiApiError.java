package org.alking.huobiapi;

public class HuobiApiError {

    private String errCode;

    private String errMsg;

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

    public HuobiApiError() {
    }

    @Override
    public String toString() {
        return java.lang.String.format("(%s,%s)",errCode, errMsg);
    }
}
