package com.example.tinkofftradingrobot.util.dto.validator;

import com.example.tinkofftradingrobot.dto.AlgorithmConfig;

public class AlgorithmConfigValidator {
    public static boolean isValid(AlgorithmConfig algorithmConfig) {
        if (algorithmConfig.isStopLossEnabled() && algorithmConfig.getStopLossBasisPoint() <= 0 ||
                algorithmConfig.getMaxPortfolioBasisPointPerInstrument() <= 0 ||
                 algorithmConfig.getStrategy() == null) {
            return false;
        }
        else {
            return true;
        }
    }
}