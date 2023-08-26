package com.fiap.burger.web.controller;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.service.ClientService;
import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.misc.exception.ClientNotFoundException;
import com.fiap.burger.web.adapter.api.client.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DefaultClientController implements ClientController {
    @Autowired
    private ClientService service;

    @Autowired
    private ClientRepository repository;

    public Client findById(@PathVariable Long clientId) {
        var persistedClient = service.findById(repository, clientId);
        if (persistedClient == null) throw new ClientNotFoundException();
        return persistedClient;
    }

    public Client findByCpf(@PathVariable String clientCpf) {
        var persistedClient = service.findByCpf(repository, clientCpf);
        if (persistedClient == null) throw new ClientNotFoundException();
        return persistedClient;
    }

    public Client insert(Client client) {
        return service.insert(repository, client);
    }
}
