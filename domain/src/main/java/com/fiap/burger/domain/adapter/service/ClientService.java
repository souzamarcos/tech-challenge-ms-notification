package com.fiap.burger.domain.adapter.service;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.entities.client.Client;

public interface ClientService {

    Client findById(ClientRepository repository, Long id);

    Client findByCpf(ClientRepository repository, String cpf);

    Client insert(ClientRepository repository, Client client);

}
