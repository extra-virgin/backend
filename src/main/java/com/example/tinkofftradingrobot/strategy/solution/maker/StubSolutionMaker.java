package com.example.tinkofftradingrobot.strategy.solution.maker;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;
import com.example.tinkofftradingrobot.strategy.solution.maker.SolutionMaker;

public class StubSolutionMaker implements SolutionMaker {
    @Override
    public SolutionResponse resolve(SolutionRequest solutionData) {
        return null;
    }
}
