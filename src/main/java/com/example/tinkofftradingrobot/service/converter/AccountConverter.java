package com.example.tinkofftradingrobot.service.converter;

import com.example.tinkofftradingrobot.dto.AccountDTO;
import com.example.tinkofftradingrobot.model.AccountEntity;

public class AccountConverter {
    public static AccountDTO toDTO(AccountEntity accountEntity) {
        return AccountDTO.builder()
                .accountId(accountEntity.getAccountId())
                .strategy(accountEntity.getStrategy()).build();
    }

    // detached
    public static AccountEntity toEntity(AccountDTO accountDTO) {
        return AccountEntity.builder()
                .id(null)
                .accountId(accountDTO.getAccountId())
                .strategy(accountDTO.getStrategy())
                .user(null).build();
    }
}
