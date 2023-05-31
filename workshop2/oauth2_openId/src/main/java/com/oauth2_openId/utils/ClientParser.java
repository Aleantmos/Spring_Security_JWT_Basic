package com.oauth2_openId.utils;

import com.oauth2_openId.entities.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClientParser {
    public static Client setClient(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(authenticationMethod -> parseAuthMethod(authenticationMethod, client))
                        .collect(Collectors.toList())
        );
        client.setGrantTypes(
                registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .map(grantType -> parseGrantType(grantType, client))
                        .collect(Collectors.toList())
        );
        client.setRedirectUrls(
                registeredClient.getRedirectUris()
                        .stream()
                        .map(url -> parseRedirectUrl(url, client))
                        .collect(Collectors.toList())
        );
        client.setScopes(
                registeredClient.getScopes()
                        .stream()
                        .map(scope -> parseScope(scope, client))
                        .collect(Collectors.toList())
        );
        return client;
    }



    public static RegisteredClient fromClient(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .clientAuthenticationMethods(clientAuthMethods(client.getAuthenticationMethods()))
                .authorizationGrantTypes(authorizationGrantTypes(client.getGrantTypes()))
                .scopes(scopes(client.getScopes()))
                .redirectUris(redirectUris(client.getRedirectUrls()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(client.getClientTokenSettings().getAccessTokenTTL()))
                        .accessTokenFormat(new OAuth2TokenFormat(client.getClientTokenSettings().getType()))
                        .build())
                .build();
    }

    private static Consumer<Set<String>> redirectUris(List<RedirectUrl> uris) {
        return r -> {
            for(RedirectUrl u : uris) {
                r.add(u.getUrl());
            }
        };

    }

    private static Consumer<Set<AuthorizationGrantType>> authorizationGrantTypes(List<GrantType> grantTypes) {
        return s -> {
            for(GrantType g : grantTypes){
                s.add(new AuthorizationGrantType(g.getGrantType()));
            }
        };
    }

    private static Consumer<Set<ClientAuthenticationMethod>> clientAuthMethods
            (List<AuthenticationMethod> authenticationMethods) {
        return m -> {
            for(AuthenticationMethod a : authenticationMethods) {
                m.add(new ClientAuthenticationMethod(a.getAuthenticationMethod()));
            }
        };

    }

    private static Consumer<Set<String>> scopes(List<Scope> scopes) {
        return s -> {
            for(Scope x : scopes) {
                s.add(x.getScope());
            }
        };
    }


    private static Scope parseScope(String scope, Client client) {
        Scope s = new Scope();
        s.setScope(scope);
        s.setClient(client);

        return s;
    }

    private static RedirectUrl parseRedirectUrl(String url, Client client) {
        RedirectUrl redirectUrl = new RedirectUrl();
        redirectUrl.setUrl(url);
        redirectUrl.setClient(client);

        return redirectUrl;
    }

    private static AuthenticationMethod parseAuthMethod(ClientAuthenticationMethod authenticationMethod, Client client) {
        AuthenticationMethod a = new AuthenticationMethod();
        a.setAuthenticationMethod(authenticationMethod.getValue());
        a.setClient(client);
        return a;
    }
    private static GrantType parseGrantType(AuthorizationGrantType authorizationGrantType, Client client) {
        GrantType grantType = new GrantType();
        grantType.setGrantType(authorizationGrantType.getValue());
        grantType.setClient(client);
        return grantType;
    }


}
