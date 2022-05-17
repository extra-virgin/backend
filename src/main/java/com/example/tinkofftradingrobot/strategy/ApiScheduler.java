package com.example.tinkofftradingrobot.strategy;

import com.example.tinkofftradingrobot.strategy.solution.SolutionInvoker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiScheduler {

    private final SolutionInvoker solutionInvoker;

    ApiScheduler(SolutionInvoker solutionInvoker) {
        this.solutionInvoker = solutionInvoker;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleSolutionInvokerRunStub() {
        solutionInvoker.runStub();
    }

}
