package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.client.Client;

public interface ClientGateway {
    Client findById(Long id);
    Client save(Client client);
    Client findByCpf(String cpf);
}
