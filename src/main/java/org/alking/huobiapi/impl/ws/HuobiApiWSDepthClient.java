package org.alking.huobiapi.impl.ws;

import okhttp3.*;
import org.alking.huobiapi.domain.resp.HuobiOrderBookResp;
import org.alking.huobiapi.domain.ws.HuobiWSDepthEvent;
import org.alking.huobiapi.domain.ws.HuobiWSSub;
import org.alking.huobiapi.misc.HuobiWSEventHandler;
import org.alking.huobiapi.util.HuobiUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.UUID;

public class HuobiApiWSDepthClient extends AbsHuobiApiWSClient {

    private static String[] VALID_TYPES = new String[]{"step0","step1","step2","step3","step4","step5"};

    private final String symbol;

    private final String type;

    public HuobiApiWSDepthClient(final String symbol,
                                 final String type,
                                 final HuobiWSEventHandler handler,
                                 final OkHttpClient client) {
        super(client,handler);
        if(StringUtils.isEmpty(symbol) || StringUtils.isEmpty(type) || handler == null){
            throw new IllegalArgumentException("symbol|type|handler not valid");
        }
        if(Arrays.stream(VALID_TYPES).noneMatch((e)-> e.equals(type))){
            throw new IllegalArgumentException("type is not valid.");
        }
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    protected HuobiWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        HuobiWSSub sub = new HuobiWSSub(String.format("market.%s.depth.%s", symbol, type), id);
        return sub;
    }

    @Override
    protected void doHandler(String json) {
        HuobiOrderBookResp resp = HuobiUtil.fromJson(json,HuobiOrderBookResp.class);
        if(resp != null && resp.valid()){
            HuobiWSDepthEvent event = resp.toDepthEvent();
            this.handler.handleDepth(event);
        }
    }

}
