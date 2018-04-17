package org.alking.huobiapi.domain.resp;


import com.google.gson.annotations.SerializedName;
import org.alking.huobiapi.domain.HuobiAccount;

import java.util.ArrayList;
import java.util.List;

public class HuobiAccountResp extends HuobiResp {

    @SerializedName("data")
    private List<HuobiAccount> accounts = new ArrayList<>();

    public List<HuobiAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<HuobiAccount> accounts) {
        this.accounts = accounts;
    }

    public HuobiAccountResp() {
    }
}
