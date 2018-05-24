package org.alking.huobiapi.misc;

import org.alking.huobiapi.domain.ws.*;

public abstract class HuobiWSEventHandler {

    public void handleDepth(HuobiWSDepthEvent event){}

    public void handleKLine(HuobiWSKLineEvent event){}

    public void handleTradeDetail(HuobiWSTradeDetailEvent event){}

    public void handleMarketDetail(HuobiWSMarketDetailEvent event){}

    public void onClosed(int code, String reason){}

    public void onFailure(String msg){}

    public void onError(HuobiWSError error){}
}
