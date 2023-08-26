package com.fiap.burger.domain.adapter.repository.client;

import com.fiap.burger.domain.entities.client.Client;

public interface ClientRepository {
    Client findById(Long id);
    Client save(Client client);
    Client findByCpf(String cpf);
}
