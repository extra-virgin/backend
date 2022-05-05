package com.example.tinkofftradingrobot.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class OrderDTO implements Serializable {
    private String figi;

    private Long price;

    private String accountID;

    private int quantity;

    private OrderDirection direction;

    private OrderType type;

    private String timestamp;
}
