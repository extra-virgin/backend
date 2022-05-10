package com.example.tinkofftradingrobot.strategy.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

@Configuration
public class Beans {
    InvestApi investApiNotSandbox;
    InvestApi investApiSandbox;
    String token1 = "t.fikyI7dHK-4bsBPZGmGkXuoUy35PhwNsk2seJZqXSQlXEhU9o7htmot8Qxmi7Uz0-a2gU42oy-YRnsb8pociqA";
    String token2 = "";

    @Bean
    InvestApi investApi() {
        investApiSandbox = InvestApi.createSandbox(token1);
        return investApiSandbox;
    }
}
