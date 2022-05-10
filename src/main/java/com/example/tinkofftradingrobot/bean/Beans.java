package com.example.tinkofftradingrobot.bean;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

@Configuration
public class Beans {
    ManagedChannel managedChannel;
    InvestApi investApi;
    String token = "t.fikyI7dHK-4bsBPZGmGkXuoUy35PhwNsk2seJZqXSQlXEhU9o7htmot8Qxmi7Uz0-a2gU42oy-YRnsb8pociqA";

    @Bean
    String token() {
        return token;
    }

    @Bean
    InvestApi investApi() {
        if (investApi == null) {
            investApi = InvestApi.createSandbox(token);
        }

        return investApi;
    }

    @Bean
    public ManagedChannel managedChannel() {
        if (managedChannel == null) {
            managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 8091)
                    .useTransportSecurity().intercept(new AuthInterceptor(token())).build();
        }
        return managedChannel;
    }

}
