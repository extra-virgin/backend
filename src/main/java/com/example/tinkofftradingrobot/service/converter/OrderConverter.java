package com.example.tinkofftradingrobot.service.converter;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.model.OrderEntity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class OrderConverter {
    public static OrderDTO toDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .figi(orderEntity.getFigi())
                .price(orderEntity.getPrice())
                .accountID(orderEntity.getAccount().getAccountId())
                .quantity(orderEntity.getQuantity())
                .direction(orderEntity.getDirection())
                .type(orderEntity.getType())
                .time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderEntity.getDate().getTime())))
                .build();
    }
}
