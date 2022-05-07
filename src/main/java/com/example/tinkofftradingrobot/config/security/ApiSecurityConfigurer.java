package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class ApiSecurityConfigurer  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final ApiAuthenticationProvider apiAuthenticationProvider;

    public ApiSecurityConfigurer(ApiAuthenticationProvider apiAuthenticationProvider) {
        this.apiAuthenticationProvider = apiAuthenticationProvider;
    }

    @Override
    public void configure(HttpSecurity http) {

    }
}
