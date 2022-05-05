package com.example.tinkofftradingrobot.service.solution;

import com.example.tinkofftradingrobot.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByLastNanos();

}
