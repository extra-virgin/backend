package com.example.tinkofftradingrobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.Instrument;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitoringService {

    private final InvestApi investApi;

    private AccountService accountService;

    @Autowired
    public void setUserService(AccountService accountService) {
        this.accountService = accountService;
    }

    public MonitoringService(InvestApi investApi) {
        this.investApi = investApi;
        List<String> list = new ArrayList<>();
        list.add("BBG000B9XRY4");
        observe(list);
    }

    public void observe(List<String> figis) {
        var instrumentsService = investApi.getInstrumentsService();
        List<Instrument> instrumentInfo = figis.stream()
                .map(instrumentsService::getInstrumentByFigiSync)
                .collect(Collectors.toList());
    }
}
