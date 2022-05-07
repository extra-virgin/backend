package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.repository.OrderRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

}
