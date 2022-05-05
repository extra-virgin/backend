package com.example.tinkofftradingrobot.util.dto.converter;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.model.OrderEntity;

public class OrderConverter {
    public static OrderDTO toDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setFigi(orderEntity.getFigi());
        orderDTO.setDirection(orderEntity.getDirection());
        orderDTO.setAccountID(orderEntity.getAccountID());
        orderDTO.setQuantity(orderEntity.getQuantity());
        orderDTO.setTimestamp(orderEntity.getTimestamp().toString());
        orderDTO.setPrice(orderEntity.getPrice());
        orderDTO.setType(orderEntity.getType());

        return orderDTO;
    }
}
