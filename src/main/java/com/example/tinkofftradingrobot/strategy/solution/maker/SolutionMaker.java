package com.example.tinkofftradingrobot.strategy.solution.maker;

import com.example.tinkofftradingrobot.strategy.solution.data.SolutionRequest;
import com.example.tinkofftradingrobot.strategy.solution.data.SolutionResponse;
import ru.tinkoff.piapi.core.InvestApi;

public interface SolutionMaker {

    SolutionResponse resolve(InvestApi channel, SolutionRequest solutionRequest);
}
