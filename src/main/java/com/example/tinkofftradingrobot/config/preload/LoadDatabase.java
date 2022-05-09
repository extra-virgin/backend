package com.example.tinkofftradingrobot.config.preload;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.service.AccountService;
import com.example.tinkofftradingrobot.service.OrderService;
import com.example.tinkofftradingrobot.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner runAccounts(AccountService accountService) {
        return args -> {

        };
    }

    @Bean
    CommandLineRunner runOrder(OrderService orderService) {
        return args -> {

        };
    }


    @Bean
    CommandLineRunner runUser(UserService userService) {
        return args -> {
            userService.addUser(new UserDTO("t.fikyI7dHK-4bsBPZGmGkXuoUy35PhwNsk2seJZqXSQlXEhU9o7htmot8Qxmi7Uz0-a2gU42oy-YRnsb8pociqA"));
        };
    }
}
