package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.model.UserEntity;
import com.example.tinkofftradingrobot.repository.UserRepo;
import com.example.tinkofftradingrobot.service.converter.UserConverter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<UserEntity> addUser(UserDTO userDTO) {
        try {
            return Optional.of(userRepo.save(UserConverter.toEntity(userDTO)));
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }
}
