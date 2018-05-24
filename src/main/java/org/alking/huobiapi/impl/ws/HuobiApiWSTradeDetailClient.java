package org.alking.huobiapi.impl.ws;

import okhttp3.OkHttpClient;
import org.alking.huobiapi.domain.resp.HuobiWSTradeDetailResp;
import org.alking.huobiapi.domain.ws.HuobiWSSub;
import org.alking.huobiapi.domain.ws.HuobiWSTradeDetailEvent;
import org.alking.huobiapi.misc.HuobiWSEventHandler;

import java.util.UUID;

public class HuobiApiWSTradeDetailClient extends AbsHuobiApiWSClient<HuobiWSTradeDetailResp> {

    private final String symbol;

    public HuobiApiWSTradeDetailClient(OkHttpClient client, HuobiWSEventHandler handler, String symbol) {
        super(client, handler, HuobiWSTradeDetailResp.class);
        this.symbol = symbol;
    }

    @Override
    protected HuobiWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        HuobiWSSub sub = new HuobiWSSub(String.format("market.%s.trade.detail", symbol), id);
        return sub;
    }

    @Override
    protected void doHandler(HuobiWSTradeDetailResp resp) {

        if(resp.tick != null && resp.tick.data != null){
            HuobiWSTradeDetailEvent event = new HuobiWSTradeDetailEvent();
            event.setSymbol(this.symbol);
            event.setTs( resp.ts );
            event.setDetails( resp.tick.data );
            if(this.handler != null){
                this.handler.handleTradeDetail(event);
            }
        }

    }
}
