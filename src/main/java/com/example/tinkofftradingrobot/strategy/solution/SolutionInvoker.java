package com.example.tinkofftradingrobot.strategy.solution;

import com.example.tinkofftradingrobot.repository.AccountRepo;
import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.strategy.Strategy;
import com.example.tinkofftradingrobot.strategy.connection.ConnectionHandler;
import com.example.tinkofftradingrobot.strategy.solution.maker.SolutionMaker;
import com.example.tinkofftradingrobot.strategy.solution.maker.StubSolutionMaker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SolutionInvoker {

    private final OrderRepo orderRepo;
    private final AccountRepo accountRepo;
    private final ConnectionHandler connectionHandler;

    private final Map<Strategy, SolutionMaker> solutionMakers;

    public SolutionInvoker(OrderRepo orderRepo, AccountRepo accountRepo, ConnectionHandler connectionHandler) {
        this.orderRepo = orderRepo;
        this.accountRepo = accountRepo;
        this.connectionHandler = connectionHandler;

        solutionMakers = new HashMap<>();
        solutionMakers.put(Strategy.STUB, new StubSolutionMaker());
    }

    public void runStub() {

    }


    private void buy() {

    }

    private void sell() {

    }
}
