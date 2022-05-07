package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(UserDTO userDTO) {
    }
}
