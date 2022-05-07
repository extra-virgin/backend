package com.example.tinkofftradingrobot.config.preload;

import com.example.tinkofftradingrobot.service.AccountService;
import com.example.tinkofftradingrobot.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner runUser(AccountService accountService) {
        return args -> {

        };
    }

    @Bean
    CommandLineRunner runOrder(OrderService orderService) {
        return args -> {

        };
    }
}
