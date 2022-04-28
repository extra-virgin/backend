package com.example.tinkofftradingrobot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
}
