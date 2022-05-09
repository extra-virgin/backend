package com.example.tinkofftradingrobot.config.security;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

class ApiSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        ApiAuthenticationProvider apiAuthenticationProvider = http.getSharedObject(ApiAuthenticationProvider.class);

        ApiAuthenticationFilter filter = new ApiAuthenticationFilter(apiAuthenticationProvider);

        http.authenticationProvider(apiAuthenticationProvider)
                .addFilterBefore(filter, OncePerRequestFilter.class);
    }
}
