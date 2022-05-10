package com.example.tinkofftradingrobot.strategy.resolver;

public interface TickerFigiResolver {
    String resolveFigi(String ticker);

    String resolveTicker(String figi);
}
