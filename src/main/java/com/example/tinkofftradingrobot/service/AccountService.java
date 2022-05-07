package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.AccountDTO;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo userRepo;

    public AccountService(AccountRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(AccountDTO accountDTO) {
    }
}
