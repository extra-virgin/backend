package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.core.AuthenticationException;

public class UnknownUserException extends AuthenticationException {
    public UnknownUserException(String string) {
        super(string);
    }
}
