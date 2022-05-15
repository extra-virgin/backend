package com.example.tinkofftradingrobot.strategy.connection;

import com.example.tinkofftradingrobot.model.UserEntity;
import com.example.tinkofftradingrobot.repository.UserRepo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.grpc.ClientInterceptors.intercept;

@Component
public class ConnectionHandler {
    private static final String tinkoffAddressName = "invest-public-api.tinkoff.ru";
    private static final Integer tinkoffAddressPort = 443;

    private final Map<String, InvestApi> connectionMap;
    private final Map<String, InvestApi> connectionMapSandbox;
    private final ManagedChannel managedChannel;
    private final UserRepo userRepo;


    public ConnectionHandler(UserRepo userRepo) {
        this.userRepo = userRepo;

        connectionMap = new HashMap<>();
        connectionMapSandbox = new HashMap<>();

        managedChannel = ManagedChannelBuilder
                .forAddress(tinkoffAddressName, tinkoffAddressPort)
                .useTransportSecurity().build();

        databaseLoadData();
    }

    private void databaseLoadData() {
        List<UserEntity> users = userRepo.findAll();

        List<String> userTokenSandboxList = users.stream()
                .filter(UserEntity::getIsSandbox)
                .map(UserEntity::getToken).collect(Collectors.toList());
        List<String> userTokenList = users.stream()
                .filter(user -> !user.getIsSandbox())
                .map(UserEntity::getToken).collect(Collectors.toList());

        loadConnectionsSandbox(userTokenSandboxList);
        loadConnections(userTokenList);
    }

    // creation
    public void createConnection(String token) {
        if (!connectionMap.containsKey(token)) {
            connectionMap.put(token, InvestApi.create(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    public void createConnectionSandbox(String token) {
        if (!connectionMapSandbox.containsKey(token)) {
            connectionMapSandbox.put(token, InvestApi.createSandbox(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    // loading
    public void loadConnections(Collection<String> tokens) {
        connectionMap.clear();
        for (var token : tokens) {
            connectionMap.put(token, InvestApi.create(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    public void loadConnectionsSandbox(Collection<String> tokens) {
        connectionMapSandbox.clear();
        for (var token : tokens) {
            connectionMapSandbox.put(token, InvestApi.createSandbox(intercept(managedChannel, new AuthInterceptor(token))));
        }
    }

    // getters
    public InvestApi getConnection(String token) {
        return connectionMap.get(token);
    }

    public InvestApi getConnectionSandbox(String token) {
        return connectionMapSandbox.get(token);
    }


    public Collection<InvestApi> getConnectionList() {
        return connectionMap.values();
    }

    public Collection<InvestApi> getConnectionListSandbox() {
        return connectionMapSandbox.values();
    }

    // removing
    public void removeConnection(String token) {
        connectionMap.remove(token);
    }

    public void removeConnectionSandbox(String token) {
        connectionMapSandbox.remove(token);
    }

    public void removeConnections(Collection<String> tokens) {
        for (var token : tokens) {
            connectionMap.remove(token);
        }
    }

    public void removeConnectionsSandbox(Collection<String> tokens) {
        for (var token : tokens) {
            connectionMapSandbox.remove(token);
        }
    }
}
