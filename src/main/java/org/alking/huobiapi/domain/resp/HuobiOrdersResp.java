package org.alking.huobiapi.domain.resp;

import com.google.gson.annotations.SerializedName;
import org.alking.huobiapi.domain.HuobiOrderInfo;

import java.util.List;

/**
 * Created by dev on 1/8/18.
 */
public class HuobiOrdersResp extends HuobiResp {

    @SerializedName("data")
    private List<HuobiOrderInfo> orderInfos;

    public List<HuobiOrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<HuobiOrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public HuobiOrdersResp() {
    }
}
