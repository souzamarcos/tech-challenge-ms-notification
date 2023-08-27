package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.entity.client.Client;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;

public interface ClientUseCase {
    Client findById(ClientGateway gateway, Long id);

    Client findByCpf(ClientGateway gateway, String cpf);

    Client insert(ClientGateway gateway, Client client);
}
