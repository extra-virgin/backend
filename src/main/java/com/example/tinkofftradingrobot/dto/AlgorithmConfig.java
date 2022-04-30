package com.example.tinkofftradingrobot.dto;

import com.example.tinkofftradingrobot.strategy.Strategy;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AlgorithmConfig implements Serializable {

    // стоп-лосс в сотых долях процента
    private int stopLossBasisPoint;
    // обязательство ставить стоп-лосс
    private boolean isStopLossEnabled;
    // макс. доля портфеля в сотых долях процента, которая может быть выделена на финансовый инструмент
    private int maxPortfolioBasisPointPerInstrument;
    // разрешается ли алгоритму шортить
    private boolean isShortEnabled;
    // торговая стратегия
    private Strategy strategy;

}
