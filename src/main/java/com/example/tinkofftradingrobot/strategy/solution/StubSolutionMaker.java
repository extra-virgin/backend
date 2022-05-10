package com.example.tinkofftradingrobot.strategy.solution;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;

public class StubSolutionMaker implements SolutionMaker {
    @Override
    public SolutionResponse resolve(SolutionRequest solutionData) {
        return null;
    }
}
