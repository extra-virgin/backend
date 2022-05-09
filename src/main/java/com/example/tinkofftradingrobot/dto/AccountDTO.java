package com.example.tinkofftradingrobot.dto;

import com.example.tinkofftradingrobot.util.Strategy;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {

    private String accountId;

    private Strategy strategy;
}
