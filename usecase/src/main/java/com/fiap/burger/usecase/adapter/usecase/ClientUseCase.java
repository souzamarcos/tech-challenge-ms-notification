package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;

public interface ClientUseCase {
    Client findById(Long id);

    Client findByCpf(String cpf);

    Client insert(Client client);
}
