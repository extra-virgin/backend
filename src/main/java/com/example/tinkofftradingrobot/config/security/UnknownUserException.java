package com.example.tinkofftradingrobot.config.security;

public class UnknownUserException extends SecurityException {
    public UnknownUserException(String string) {
        super(string);
    }
}
