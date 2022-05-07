package com.example.tinkofftradingrobot.config.preload;

import com.example.tinkofftradingrobot.service.UserService;
import com.example.tinkofftradingrobot.service.solution.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner runUser(UserService userService) {
        return args -> {

        };
    }

    @Bean
    CommandLineRunner runOrder(OrderService orderService) {
        return args -> {

        };
    }
}
