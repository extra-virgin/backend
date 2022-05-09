package com.example.tinkofftradingrobot.strategy.solution;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;

public interface SolutionMaker {

    public SolutionResponse resolve(SolutionRequest solutionRequest);
}
