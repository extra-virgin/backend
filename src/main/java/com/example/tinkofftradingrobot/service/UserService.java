package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.repository.UserRepo;
import com.example.tinkofftradingrobot.service.converter.UserConverter;
import com.example.tinkofftradingrobot.strategy.connection.ConnectionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ConnectionHandler connectionHandler;

    public UserService(UserRepo userRepo, ConnectionHandler connectionHandler) {
        this.userRepo = userRepo;
        this.connectionHandler = connectionHandler;
    }

    public Optional<UserDTO> addUser(UserDTO userDTO, Boolean isSandbox) {
        try {
            // create entity
            isSandbox = isSandbox != null && isSandbox;
            var userEntity = UserConverter.toEntity(userDTO);
            userEntity.setIsSandbox(isSandbox);
            // saving
            userEntity = userRepo.save(userEntity);
            // connection
            if (isSandbox) {
                connectionHandler.createConnectionSandbox(userEntity.getToken());
            } else {
                connectionHandler.createConnection(userEntity.getToken());
            }
            return Optional.of(UserConverter.toDTO(userEntity));
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> removeUser(UserDTO userDTO) {
        try {
            var userEntityOpt = userRepo.findByToken(userDTO.getToken());
            if (userEntityOpt.isPresent()) {
                var userEntity = userEntityOpt.get();
                userRepo.delete(userEntity);
                // connection
                if (userEntity.getIsSandbox()) {
                    connectionHandler.removeConnectionSandbox(userEntity.getToken());
                } else {
                    connectionHandler.removeConnection(userEntity.getToken());
                }
            }
            return userEntityOpt.map(UserConverter::toDTO);
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }
}
