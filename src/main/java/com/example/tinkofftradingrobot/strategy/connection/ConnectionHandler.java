package com.example.tinkofftradingrobot.strategy.connection;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import ru.tinkoff.piapi.core.InvestApi;

import static io.grpc.ClientInterceptors.intercept;

import java.util.*;

@Component
public class ConnectionHandler {
    private static final String tinkoffAddressName = "invest-public-api.tinkoff.ru";
    private static final Integer tinkoffAddressPort = 443;

    private final Map<String, InvestApi> connectionMap;
    private final ManagedChannel managedChannel;


    public ConnectionHandler() {
        connectionMap = new HashMap<>();
        managedChannel = ManagedChannelBuilder
                .forAddress(tinkoffAddressName, tinkoffAddressPort)
                .useTransportSecurity().build();
    }


    public void createConnection(String token) {
        if (!connectionMap.containsKey(token)) {
            connectionMap.put(token, InvestApi.create(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    public void createConnectionSandbox(String token) {
        if (!connectionMap.containsKey(token)) {
            connectionMap.put(token, InvestApi.createSandbox(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    public void loadConnections(Collection<String> tokens) {
        connectionMap.clear();
        for (var token : tokens) {
            connectionMap.put(token, InvestApi.create(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    public void loadConnectionsSandbox(Collection<String> tokens) {
        connectionMap.clear();
        for (var token : tokens) {
            connectionMap.put(token, InvestApi.createSandbox(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    public void removeConnection(String token) {
        connectionMap.remove(token);
    }

    public void removeConnections(Collection<String> tokens) {
        for (var token : tokens) {
            connectionMap.remove(token);
        }
    }
}
