package com.example.tinkofftradingrobot.config;

import com.example.tinkofftradingrobot.dto.AlgorithmConfig;
import com.example.tinkofftradingrobot.strategy.Strategy;

public class AlgorithmConfigKeeper {
    private static AlgorithmConfig ALGORITHM_CONFIG;

    public static AlgorithmConfig getConfig() {
        return ALGORITHM_CONFIG;
    }

    public static int stopLossBasisPoint() {
        return ALGORITHM_CONFIG.getStopLossBasisPoint();
    }

    public static boolean isStopLossEnabled() {
        return ALGORITHM_CONFIG.isStopLossEnabled();
    }

    public static int maxPortfolioBasisPointPerInstrument() {
        return ALGORITHM_CONFIG.getMaxPortfolioBasisPointPerInstrument();
    }

    public static boolean isShortEnabled() {
        return ALGORITHM_CONFIG.isShortEnabled();
    }

    // торговая стратегия
    private Strategy strategy;

    // ToDo: такие дела надо будет обязательно логировать
    public static void setAlgorithmConfig(AlgorithmConfig algorithmConfig) {
        ALGORITHM_CONFIG = algorithmConfig;
    }

}
