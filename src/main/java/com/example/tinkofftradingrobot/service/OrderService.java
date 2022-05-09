package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.model.OrderEntity;
import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.service.converter.OrderConverter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<OrderDTO> getOrdersForLastMillis(long time) {
        List<OrderDTO> orders = new ArrayList<>();
        for (OrderEntity o : orderRepo.findAllWithDateTimeBefore(new Date(time))) {
            orders.add(OrderConverter.toDTO(o));
        }

        return orders;
    }
}
