package com.example.tinkofftradingrobot.strategy.connection;

import io.grpc.*;

public class AuthInterceptor implements ClientInterceptor {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final Metadata.Key<String> AUTH_HEADER = Metadata.Key.of(AUTH_HEADER_NAME,
            Metadata.ASCII_STRING_MARSHALLER);

    private final String authHeaderValue;

    public AuthInterceptor(String token) {
        this.authHeaderValue = "Bearer " + token;
    }


    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
                                                               CallOptions callOptions, Channel channel) {
        var call = channel.newCall(methodDescriptor, callOptions);
        return new ForwardingClientCall.SimpleForwardingClientCall<>(call) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(AUTH_HEADER, authHeaderValue);
                super.start(responseListener, headers);
            }
        };
    }
}