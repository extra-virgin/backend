package com.example.tinkofftradingrobot.service.converter;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.model.UserEntity;

public class UserConverter {
    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .accountId(userEntity.getAccountId())
                .strategy(userEntity.getStrategy()).build();
    }
    // detached
    public static UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .userId(null)
                .accountId(userDTO.getAccountId())
                .strategy(userDTO.getStrategy()).build();
    }
}
