package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiAuthenticationToken extends AbstractAuthenticationToken {

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
