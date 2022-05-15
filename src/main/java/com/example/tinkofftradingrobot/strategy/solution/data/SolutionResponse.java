package com.example.tinkofftradingrobot.strategy.solution.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionResponse {
    OrderDirection orderDirection;
    int quantity;
    String accountID;
    String figi;
    OrderType orderType;
    String orderID;
}
