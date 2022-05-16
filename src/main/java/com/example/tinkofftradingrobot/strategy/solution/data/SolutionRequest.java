package com.example.tinkofftradingrobot.strategy.solution.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionRequest {
    String accountID;
    List<String> figi;
}
