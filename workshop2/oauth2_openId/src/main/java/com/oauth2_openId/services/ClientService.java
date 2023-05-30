package com.oauth2_openId.services;

import com.oauth2_openId.entities.Client;
import com.oauth2_openId.repository.ClientRepository;
import com.oauth2_openId.utils.ClientParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientService  implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        Client client = ClientParser.setClient(registeredClient);
        clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        var client = clientRepository.findById(Integer.parseInt(id));

        return client.map(ClientParser::fromClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client = clientRepository.findByClientId(clientId);

        return client.map(ClientParser::fromClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    // use only for the example
    @Bean
    private PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
