package com.example.tinkofftradingrobot.service.resolver;

public interface TickerFigiResolver {
    String resolveFigi(String ticker);
    String resolveTicker(String figi);
}
