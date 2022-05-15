package com.example.tinkofftradingrobot.strategy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiScheduler {

    private final SolutionInvoker solutionInvoker;

    ApiScheduler(SolutionInvoker solutionInvoker) {
        this.solutionInvoker = solutionInvoker;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        solutionInvoker.run();
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void updateActiveAccounts() {
        solutionInvoker.updateActiveAccounts();;
    }

}