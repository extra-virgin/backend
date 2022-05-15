package com.example.tinkofftradingrobot.strategy.solution.maker;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;
import ru.tinkoff.piapi.core.InvestApi;

public class StubSolutionMaker implements SolutionMaker {

    @Override
    public SolutionResponse resolve(InvestApi channel, SolutionRequest solutionData) {
        if (channel.isSandboxMode()) {
            return resolveSandbox(channel, solutionData);
        } else {
            return resolvePublic(channel, solutionData);
        }
    }

    public SolutionResponse resolveSandbox(InvestApi channel, SolutionRequest solutionRequest) {
        return null;
    }

    public SolutionResponse resolvePublic(InvestApi channel, SolutionRequest solutionRequest) {
        return null;
    }
}
