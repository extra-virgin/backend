package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.OrderDTO;
import com.example.tinkofftradingrobot.model.AccountEntity;
import com.example.tinkofftradingrobot.model.OrderEntity;
import com.example.tinkofftradingrobot.model.UserEntity;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.repository.UserRepo;
import com.example.tinkofftradingrobot.service.converter.OrderConverter;
import com.example.tinkofftradingrobot.strategy.connection.ConnectionHandler;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;
    private final ConnectionHandler connectionHandler;


    public OrderService(OrderRepo orderRepo, AccountRepo accountRepo, UserRepo userRepo, ConnectionHandler connectionHandler) {
        this.orderRepo = orderRepo;
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.connectionHandler = connectionHandler;
    }

    public List<OrderDTO> getOrdersForLastMillis(String token, String accountId, long time) {
        var accountOpt = accountRepo.findAccountEntityByAccountId(accountId);
        if (accountOpt.isEmpty() || isUserOrAccountNotValid(userRepo.findByToken(token), accountOpt)) return null;

        List<OrderDTO> orders = new ArrayList<>();

        for (OrderEntity o : orderRepo.findAllByAccountAndDateBefore(accountOpt.get(), new Date(time))) {
            orders.add(OrderConverter.toDTO(o));
        }
        return orders;
    }

    public List<OrderDTO> getAllByAccountId(String token, String accountId) {
        var accountOpt = accountRepo.findAccountEntityByAccountId(accountId);
        if (accountOpt.isEmpty() || isUserOrAccountNotValid(userRepo.findByToken(token), accountOpt)) return null;

        return orderRepo.getAllByAccount(accountOpt.get()).stream().map(OrderConverter::toDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getLastCountByAccountId(String token, String accountId, long count) {
        var accountOpt = accountRepo.findAccountEntityByAccountId(accountId);
        if (accountOpt.isEmpty() || isUserOrAccountNotValid(userRepo.findByToken(token), accountOpt)) return null;

        return orderRepo.findAllByAccountOrderByOrderIdDesc(accountOpt.get()).stream()
                .limit(count)
                .map(OrderConverter::toDTO)
                .collect(Collectors.toList());
    }

    private boolean isUserOrAccountNotValid(Optional<UserEntity> userOpt, Optional<AccountEntity> accountOpt) {
        if (userOpt.isEmpty() || accountOpt.isEmpty()) return true;
        var account = accountOpt.get();
        var user = userOpt.get();
        return !account.getUser().getToken().equals(user.getToken());
    }
}
