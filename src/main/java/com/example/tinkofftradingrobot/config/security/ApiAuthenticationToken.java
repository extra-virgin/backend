package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class ApiAuthenticationToken implements Authentication {

    private final String token;
    private boolean isAuthenticated = false;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public static ApiAuthenticationToken getApiAuthTokenFromContext() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof ApiAuthenticationToken) {
            return (ApiAuthenticationToken) auth;
        }
        throw new UnknownUserException("ApiAuthenticationToken not found");
    }
}
