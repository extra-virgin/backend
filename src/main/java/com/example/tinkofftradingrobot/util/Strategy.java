package com.example.tinkofftradingrobot.util;

public enum Strategy {
    NONE("NONE"),
    STUB("STUB");

    private final String value;

    Strategy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
