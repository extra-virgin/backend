package com.example.tinkofftradingrobot.service.converter;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.model.OrderEntity;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderConverter {
    public static OrderDTO toDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .figi(orderEntity.getFigi())
                .price(orderEntity.getPrice())
                .accountID(orderEntity.getAccount().getAccountId())
                .quantity(orderEntity.getQuantity())
                .direction(orderEntity.getDirection())
                .type(orderEntity.getType())
                .time(orderEntity.getDate().getTime())
                .build();
    }
    // detached (no user)
    public static OrderEntity toEntity(OrderDTO orderDTO) {
        return OrderEntity.builder()
                .orderId(null)
                .figi(orderDTO.getFigi())
                .price(orderDTO.getPrice())
                .account(null)
                .quantity(orderDTO.getQuantity())
                .direction(orderDTO.getDirection())
                .type(orderDTO.getType())
                .date(new Date(orderDTO.getTime()))
                .build();
    }
}
