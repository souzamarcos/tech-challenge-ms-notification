package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.entity.client.Client;

public interface ClientController {
    Client findById(Long clientId);

    Client findByCpf(String cpf);

    Client insert(Client client);
}
