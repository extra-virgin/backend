package com.example.tinkofftradingrobot.strategy.solution;

import com.example.tinkofftradingrobot.config.security.ApiAuthenticationToken;
import com.example.tinkofftradingrobot.model.AccountEntity;
import com.example.tinkofftradingrobot.model.OrderEntity;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.strategy.Strategy;
import com.example.tinkofftradingrobot.strategy.connection.ConnectionHandler;
import com.example.tinkofftradingrobot.strategy.solution.data.Resolution;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;
import com.example.tinkofftradingrobot.strategy.solution.maker.SolutionMaker;
import com.example.tinkofftradingrobot.strategy.solution.maker.StubSolutionMaker;
import org.springframework.stereotype.Component;
import ru.tinkoff.piapi.contract.v1.OrderExecutionReportStatus;
import ru.tinkoff.piapi.core.InvestApi;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SolutionInvoker {

    Logger logger = Logger.getLogger(SolutionInvoker.class.getName());

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
            SolutionResponse solutionResponse = stubSolutionMaker.resolve(connection.get(), solutionRequest.get());
            if (!solutionResponse.getResolutions().isEmpty()) {
                for (Resolution resolution : solutionResponse.getResolutions()) {
                    executeResolution(resolution, solutionResponse.getAccountID());
                }
            }
        }
    }

    private void executeResolution(Resolution resolution, String accountID) {
        String token = ApiAuthenticationToken.getApiAuthTokenFromContext().getName();
        connectionHandler.getConnection(token).getSandboxService().postOrder(resolution.getFigi(), resolution.getQuantity(),
                resolution.getPrice(), resolution.getOrderDirection(), accountID, resolution.getOrderType(), resolution.getOrderID())
                .thenAccept(x -> {
                    var status = x.getExecutionReportStatus();
                    if (status != OrderExecutionReportStatus.EXECUTION_REPORT_STATUS_REJECTED) {
                        OrderEntity orderEntity = new OrderEntity();
                        orderEntity.setDirection(x.getDirection());
                        orderEntity.setFigi(x.getFigi());
                        orderEntity.setPrice(x.getInitialOrderPrice().getUnits());
                        orderEntity.setQuantity(x.getLotsExecuted());
                        orderEntity.setType(x.getOrderType());
                        orderEntity.setAccount(accountRepo.findAccountEntityByAccountId(accountID)
                                .orElse(AccountEntity.builder().accountId(accountID).strategy(Strategy.STUB).build()));
                        orderEntity.setDate((Date) Date.from(Instant.now()));

                        orderRepo.save(orderEntity);
                        logger.log(Level.ALL, x.toString());
                    }
                });
    }

}
