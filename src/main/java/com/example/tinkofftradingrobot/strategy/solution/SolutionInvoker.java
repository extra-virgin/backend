package com.example.tinkofftradingrobot.strategy.solution;

import com.example.tinkofftradingrobot.model.AccountEntity;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.strategy.Strategy;
import com.example.tinkofftradingrobot.strategy.connection.ConnectionHandler;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.maker.SolutionMaker;
import com.example.tinkofftradingrobot.strategy.solution.maker.StubSolutionMaker;
import org.springframework.stereotype.Component;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
        StubSolutionMaker stubSolutionMaker = (StubSolutionMaker) solutionMakers.get(Strategy.STUB);
        AtomicReference<SolutionRequest> solutionRequest = new AtomicReference<>();
        Optional<InvestApi> connection = connectionHandler.getConnectionListSandbox().stream().filter(
                api -> {
                    return api.getUserService().getAccountsSync().stream()
                            .anyMatch(account -> {
                                if(accountRepo.existsByAccountId(account.getId())) {
                                    Optional<AccountEntity> accountEntity = accountRepo.findAccountEntityByAccountId(account.getId());
                                    if (accountEntity.isPresent()) {
                                        solutionRequest.set(new SolutionRequest());
                                        solutionRequest.get().setAccountID(accountEntity.get().getAccountId());
                                        solutionRequest.get().setFigi(accountEntity.get().getFigis());
                                        return true;
                                    }
                                }
                                return false;
                            });
                }
        ).findFirst();
        if (connection.isPresent() && !solutionRequest.get().getAccountID().isEmpty()) {
            stubSolutionMaker.resolve(connection.get(), solutionRequest.get());
        }
    }


    private void buy() {

    }

    private void sell() {

    }
}
