package com.example.tinkofftradingrobot.config.security;

import com.example.tinkofftradingrobot.model.UserEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiAuthenticationToken implements Authentication {

    private final String token;
    boolean isAuthenticated = false;
    Long userId;

    public ApiAuthenticationToken(String token) {
        this.token = token;
    }

    public ApiAuthenticationToken(String token, Long userId, boolean isAuthenticated) {
        this.token = token;
        this.isAuthenticated = isAuthenticated;
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }
}
