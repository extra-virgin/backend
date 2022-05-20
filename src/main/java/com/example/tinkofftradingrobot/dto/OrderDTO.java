package com.example.tinkofftradingrobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

import java.io.Serializable;

@Data
@Builder
public class OrderDTO implements Serializable {
    private String figi;

    private Long price;

    @JsonProperty("account_id")
    private String accountID;

    private Long quantity;

    private OrderDirection direction;

    private OrderType type;

    private String time;
}
