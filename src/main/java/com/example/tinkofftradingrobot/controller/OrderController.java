package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tinkofftradingrobot.config.security.ApiAuthenticationToken.getApiAuthTokenFromContext;

import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/time")
    public ResponseEntity<?> getOrdersForLastMillis(@RequestParam("account_id") String accountId, @RequestParam Long time) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null || accountId == null || time == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<OrderDTO> orders = orderService.getOrdersForLastMillis(auth.getName(), accountId, time);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllOrdersFromAccount(@RequestParam("account_id") String accountId) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null || accountId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var orders = orderService.getAllByAccountId(auth.getName(), accountId);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> getLastNOrdersFromAccount(@RequestParam("account_id") String accountId, @RequestParam Long count) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null || count == null || accountId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var orders = orderService.getAllByAccountId(auth.getName(), accountId);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
    }
}
