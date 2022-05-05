package com.example.tinkofftradingrobot.service.solution;

import com.example.tinkofftradingrobot.model.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> getOrdersByLastNanos();

}
