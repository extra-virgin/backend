package com.example.tinkofftradingrobot.service.converter;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.model.UserEntity;

public class UserConverter {
    public static UserDTO toDTO(UserEntity userEntity) {
        var userDTO = new UserDTO();
        userDTO.setToken(userEntity.getToken());
        return userDTO;
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        var userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setToken(userDTO.getToken());
        userEntity.setAccountEntityList(null);
        userEntity.setIsSandbox(null);
        return userEntity;
    }
}
