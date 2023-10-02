package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.client.Client;

public interface ClientUseCase {
    Client findById(Long id);

    Client findByCpf(String cpf);

    Client insert(Client client);
}
