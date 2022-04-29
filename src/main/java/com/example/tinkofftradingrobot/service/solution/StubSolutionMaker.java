package com.example.tinkofftradingrobot.service.solution;

import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;
import java.util.UUID;

/**
 *  Mock of real strategy class for testing.
 *  Randomly buys an instrument then sells if price grows by 1% or if it falls by 0.5%
 */
public class StubSolutionMaker implements StrategySolutionMaker {
    private InvestApi investApi;

    public StubSolutionMaker(InvestApi investApi) {
        this.investApi = investApi;
    }

    @Override
    // тут вместо figi должен быть объект со всей информацией, которая может пригодиться для принятия решения.
    // вызывается из MonitoringService
    public void resolve(String figi) {
        if (Math.random() > 0.9) {
            buy(figi);
        }
    }

    private void buy(String figi) {
        investApi.getOrdersService().postOrder(figi, 1L, investApi
                .getMarketDataService().getLastPricesSync(List.of("figi")).get(0).getPrice(),
                OrderDirection.ORDER_DIRECTION_BUY, investApi.getUserService().getAccountsSync().get(0).getId(),
                OrderType.ORDER_TYPE_MARKET, generateOrderID());
    }

    private void sell(String figi) {

    }

    // важно! должна соблюдаться идемподентность
    private String generateOrderID() {
        return String.valueOf(UUID.randomUUID());
    }
}
