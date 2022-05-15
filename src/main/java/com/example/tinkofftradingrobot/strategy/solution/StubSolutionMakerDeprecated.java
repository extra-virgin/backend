package com.example.tinkofftradingrobot.strategy.solution;

import com.example.tinkofftradingrobot.config.AlgorithmConfigKeeper;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Mock of real strategy class for testing.
 * Randomly buys an instrument then sells if price grows by 1% or if it falls by 0.5%
 */
//@Service
public class StubSolutionMakerDeprecated {
    private InvestApi investApi;

    public StubSolutionMakerDeprecated(InvestApi investApi) {
        this.investApi = investApi;
    }

    // тут вместо figi должен быть объект со всей информацией, которая может пригодиться для принятия решения.
    // вызывается из MonitoringService
    public void resolve(String figi) {
        if (Math.random() > 0.9) {
            String accountId = investApi.getUserService().getAccountsSync().get(0).getId();
            var positions = investApi.getOperationsService().getPortfolioSync(accountId).getPositions();

            buy(figi);
        }
    }

    public void resolveOpenedPosition(String figi) {

    }

    /**
     * Возможность купить (необходимо соблюдение всех условий):
     * 1) текущая_цена * (кол-во_в_портфеле + 1) < (капитал * макс_базисных_пунктов_на_инструмент)/10000
     * 2) текущая_цена * 1 < свободные_деньги_в_соответствующей_валюте
     */

    private void buy(String figi) {
        var accountID = investApi.getUserService().getAccountsSync().get(0).getId();
        var currentPrice = investApi.getMarketDataService().getLastPricesSync(List.of(figi)).get(0).getPrice().getUnits() * 100 +
                investApi.getMarketDataService().getLastPricesSync(List.of(figi)).get(0).getPrice().getNano();
        var portfolio = investApi.getOperationsService().getPortfolioSync(accountID);
        var positionList = portfolio.getPositions();
        var amount = 0;
        for (var pos : positionList) {
            if (pos.getFigi().equals(figi)) {
                amount = pos.getQuantity().intValue();
            }
        }
        var capital = portfolio.getTotalAmountBonds().getValue().intValue() + portfolio.getTotalAmountEtfs().getValue().intValue() +
                portfolio.getTotalAmountCurrencies().getValue().intValue() + portfolio.getTotalAmountShares().getValue().intValue() +
                portfolio.getTotalAmountFutures().getValue().intValue();
        var rubles = 0;
        if (Objects.equals(portfolio.getTotalAmountCurrencies().getCurrency().getCurrencyCode(), "RUB")) {
            rubles = portfolio.getTotalAmountCurrencies().getValue().intValue();
        }

        if (currentPrice * (amount + 1) < (long) capital * AlgorithmConfigKeeper.maxPortfolioBasisPointPerInstrument()
                && capital > 0 && currentPrice < rubles) {
            investApi.getOrdersService().postOrder(figi, 1L, investApi
                            .getMarketDataService().getLastPricesSync(List.of(figi)).get(0).getPrice(),
                    OrderDirection.ORDER_DIRECTION_BUY, investApi.getUserService().getAccountsSync().get(0).getId(),
                    OrderType.ORDER_TYPE_MARKET, generateOrderID());
        } else {
            // выкинуть эксепшн
        }
    }

    private void sell(String figi) {
        investApi.getOrdersService().postOrder(figi, 1L, investApi
                        .getMarketDataService().getLastPricesSync(List.of(figi)).get(0).getPrice(),
                OrderDirection.ORDER_DIRECTION_SELL, investApi.getUserService().getAccountsSync().get(0).getId(),
                OrderType.ORDER_TYPE_MARKET, generateOrderID());
    }

    // важно! должна соблюдаться идемподентность
    private String generateOrderID() {
        return String.valueOf(UUID.randomUUID());
    }

}
