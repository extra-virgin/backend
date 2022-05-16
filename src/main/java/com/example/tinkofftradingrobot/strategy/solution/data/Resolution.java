package com.example.tinkofftradingrobot.strategy.solution.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resolution {
    OrderDirection orderDirection;
    int quantity;
    String figi;
    OrderType orderType;
    String orderID;
}
