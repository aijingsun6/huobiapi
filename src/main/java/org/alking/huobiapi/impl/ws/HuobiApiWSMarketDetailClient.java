package org.alking.huobiapi.impl.ws;

import okhttp3.OkHttpClient;
import org.alking.huobiapi.domain.resp.HuobiWSMarketDetailResp;
import org.alking.huobiapi.domain.ws.HuobiWSMarketDetailEvent;
import org.alking.huobiapi.domain.ws.HuobiWSSub;
import org.alking.huobiapi.misc.HuobiWSEventHandler;

import java.util.UUID;

public class HuobiApiWSMarketDetailClient extends AbsHuobiApiWSClient<HuobiWSMarketDetailResp> {

    private final String symbol;

    public HuobiApiWSMarketDetailClient(OkHttpClient client, HuobiWSEventHandler handler, String symbol) {
        super(client, handler, HuobiWSMarketDetailResp.class);
        this.symbol = symbol;
    }

    @Override
    protected HuobiWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        return new HuobiWSSub(String.format("market.%s.detail", symbol), id);
    }

    @Override
    protected void doHandler(HuobiWSMarketDetailResp resp) {
        if (this.handler != null && resp != null && resp.tick != null) {
            HuobiWSMarketDetailEvent event = new HuobiWSMarketDetailEvent();
            event.setTs(resp.ts);
            event.setData(resp.tick);
            event.setSymbol(this.symbol);
            this.handler.handleMarketDetail(event);
        }
    }
}
