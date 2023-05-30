package com.oauth2_openId.services;

import com.oauth2_openId.entities.Client;
import com.oauth2_openId.repository.ClientRepository;
import com.oauth2_openId.utils.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService  implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        Client client = Parser.setClient(registeredClient);
        clientRepository.save(c);
    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }

    // use only for the example
    @Bean
    private PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
