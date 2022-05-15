package com.example.tinkofftradingrobot.strategy.resolver;

import ru.tinkoff.piapi.core.InvestApi;

//@Service
public class FuturesTickerFigiResolver implements TickerFigiResolver {
    private final String FUTURES_CLASS_CODE = "SPBFUT";

    private InvestApi investApi;

    public FuturesTickerFigiResolver(InvestApi investApi) {
        this.investApi = investApi;
    }

    @Override
    public String resolveFigi(String ticker) {
        return investApi.getInstrumentsService().getInstrumentByTickerSync(ticker, FUTURES_CLASS_CODE).getFigi();
    }

    @Override
    public String resolveTicker(String figi) {
        return investApi.getInstrumentsService().getFutureByFigiSync(figi).getTicker();
    }
}
