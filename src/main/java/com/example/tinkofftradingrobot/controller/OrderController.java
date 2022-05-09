package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getOrdersForLastMillis(@RequestParam long time) {
        List<OrderDTO> orders = orderService.getOrdersForLastMillis(time);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
