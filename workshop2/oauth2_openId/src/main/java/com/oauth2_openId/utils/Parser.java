package com.oauth2_openId.utils;

import com.oauth2_openId.entities.AuthenticationMethod;
import com.oauth2_openId.entities.Client;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.stream.Collectors;

public class Parser {


    public static Client setClient(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(authenticationMethod -> from(authenticationMethod, client))
                        .collect(Collectors.toList())
        );
        return client;
    }

    private static AuthenticationMethod from(ClientAuthenticationMethod authenticationMethod, Client client) {
        AuthenticationMethod a = new AuthenticationMethod();
        a.setAuthenticationMethod(authenticationMethod.getValue());
        a.setClient(client);
        return a;
    }


}
