package com.fiap.burger.web.adapter.api.client;

import com.fiap.burger.domain.entities.client.Client;

public interface ClientController {
    Client findById(Long clientId);

    Client findByCpf(String cpf);

    Client insert(Client client);
}
