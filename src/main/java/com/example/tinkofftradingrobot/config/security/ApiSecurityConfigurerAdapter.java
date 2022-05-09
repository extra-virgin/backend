package com.example.tinkofftradingrobot.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
public class ApiSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final ApiAuthenticationProvider apiAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ApiAuthenticationFilter filter = new ApiAuthenticationFilter(apiAuthenticationProvider);
        http.authenticationProvider(apiAuthenticationProvider).addFilterAfter(filter, BasicAuthenticationFilter.class);
    }
}
