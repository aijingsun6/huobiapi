package org.alking.huobiapi.misc;

import org.alking.huobiapi.domain.ws.HuobiWSDepthEvent;
import org.alking.huobiapi.domain.ws.HuobiWSError;
import org.alking.huobiapi.domain.ws.HuobiWSKLineEvent;

public abstract class HuobiWSEventHandler {

    public void handleDepth(HuobiWSDepthEvent event){}

    public void handleKLine(HuobiWSKLineEvent event){}

    public void onClosed(int code, String reason){}

    public void onFailure(String msg){}

    public void onError(HuobiWSError error){};
}
