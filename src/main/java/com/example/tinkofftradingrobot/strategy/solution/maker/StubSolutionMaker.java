package com.example.tinkofftradingrobot.strategy.solution.maker;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;
import io.grpc.Channel;

public class StubSolutionMaker implements SolutionMaker {
    @Override
    public SolutionResponse resolve(Channel channel, SolutionRequest solutionData) {
        return null;
    }
}
