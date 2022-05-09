package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.core.GrantedAuthority;

public enum ApiRole implements GrantedAuthority {
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
