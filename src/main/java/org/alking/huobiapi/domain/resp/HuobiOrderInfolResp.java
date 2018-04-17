package org.alking.huobiapi.domain.resp;

import com.google.gson.annotations.SerializedName;
import org.alking.huobiapi.domain.HuobiOrderInfo;

public class HuobiOrderInfolResp extends HuobiResp {

    @SerializedName("data")
    private HuobiOrderInfo orderDetail;

    public HuobiOrderInfo getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(HuobiOrderInfo orderDetail) {
        this.orderDetail = orderDetail;
    }

    public HuobiOrderInfolResp() {
    }
}
