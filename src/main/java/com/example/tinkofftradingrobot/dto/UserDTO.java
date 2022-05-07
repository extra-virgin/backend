package com.example.tinkofftradingrobot.dto;

import com.example.tinkofftradingrobot.strategy.Strategy;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String accountId;

    private Strategy strategy;
}
