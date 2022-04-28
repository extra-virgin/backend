package com.example.tinkofftradingrobot.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

@Service
public class MonitoringService {

    private final InvestApi investApi;

    public MonitoringService(InvestApi investApi) {
        this.investApi = investApi;
    }

    public void observe(List<String> figis) {

    }
}
