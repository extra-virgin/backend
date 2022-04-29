package com.example.tinkofftradingrobot.service.solution;

import org.springframework.stereotype.Service;

@Service
public interface StrategySolutionMaker {
    void resolve(String figi);
}
