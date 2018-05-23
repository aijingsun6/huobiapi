package org.alking.huobiapi.misc;

import org.alking.huobiapi.domain.ws.HuobiWSDepthEvent;

public interface HuobiWSEventHandler {

    void handleDepth(HuobiWSDepthEvent event);
}
