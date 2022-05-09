package com.example.tinkofftradingrobot.config;

import com.example.tinkofftradingrobot.config.security.ApiAuthenticationProvider;
import com.example.tinkofftradingrobot.config.security.ApiSecurityConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableScheduling
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApiAuthenticationProvider apiAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // enable CORS support
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        // http basic authentication
        http.httpBasic().disable();
        // disable cross site request forgery
        http.csrf().disable();
        // disable session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/user/create").permitAll()
                .anyRequest().authenticated();
        http.apply(new ApiSecurityConfigurerAdapter(apiAuthenticationProvider));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(apiAuthenticationProvider);
    }
}
