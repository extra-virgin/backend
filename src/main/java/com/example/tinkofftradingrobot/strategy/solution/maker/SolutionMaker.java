package com.example.tinkofftradingrobot.strategy.solution.maker;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;
import io.grpc.Channel;

public interface SolutionMaker {

    public SolutionResponse resolve(Channel channel, SolutionRequest solutionRequest);
}
