package com.example.tinkofftradingrobot.dto;

import com.example.tinkofftradingrobot.util.Strategy;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {

    @JsonProperty("account_id")
    private String accountId;

    private Strategy strategy;
}
