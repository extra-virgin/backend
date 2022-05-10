package com.example.tinkofftradingrobot.strategy;

import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.strategy.solution.SolutionMaker;
import com.example.tinkofftradingrobot.strategy.solution.StubSolutionMaker;
import com.example.tinkofftradingrobot.util.Strategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SolutionInvoker {

    private final OrderRepo orderRepo;
    private final Map<Strategy, SolutionMaker> solutionMakers;

    public SolutionInvoker(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;

        solutionMakers = new HashMap<>();
        solutionMakers.put(Strategy.STUB, new StubSolutionMaker());
    }

    public void run() {
        // здесь будет делаться вся работа

    }


    private void buy() {

    }

    private void sell() {

    }
}
