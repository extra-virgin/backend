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

    public Optional<UserDTO> addUser(UserDTO userDTO) {
        try {
            var userEntity = userRepo.save(UserConverter.toEntity(userDTO));
            return Optional.of(UserConverter.toDTO(userEntity));
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> removeUser(UserDTO userDTO) {
        try {
            var userEntityOpt = userRepo.findByToken(userDTO.getToken());
            userEntityOpt.ifPresent(userRepo::delete);
            return userEntityOpt.map(UserConverter::toDTO);
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }
}
