package com.example.tinkofftradingrobot.service.converter;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.model.OrderEntity;

import java.sql.Timestamp;

public class OrderConverter {
    public static OrderDTO toDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .figi(orderEntity.getFigi())
                .direction(orderEntity.getDirection())
                .accountID(orderEntity.getAccount().getAccountId())
                .quantity(orderEntity.getQuantity())
                .timestamp(orderEntity.getTimestamp().toString())
                .price(orderEntity.getPrice())
                .type(orderEntity.getType())
                .token(null)
                .build();
    }
    /*// detached (no user)
    public static OrderEntity toEntity(OrderDTO orderDTO) {
        return OrderEntity.builder()
                .orderId(null)
                .figi(orderDTO.getFigi())
                .direction(orderDTO.getDirection())
                .quantity(orderDTO.getQuantity())
                .timestamp(Timestamp.valueOf(orderDTO.getTimestamp()))
                .price(orderDTO.getPrice())
                .type(orderDTO.getType())
                .user(null)
                .build();
    }
     */
}
