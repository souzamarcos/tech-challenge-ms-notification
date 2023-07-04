package com.fiap.burger.domain.adapter.repository.client;

import com.fiap.burger.domain.entities.client.Client;

import java.util.Optional;

public interface ClientRepository {

    Client findById(Long id);
    Client save(Client client);

    Client findByCpf(String cpf);
}
