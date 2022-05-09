package com.example.tinkofftradingrobot.util;

public enum Strategy {
    STUB("STUB");

    private final String value;

    private Strategy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
