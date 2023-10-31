package com.fiap.burger.gateway.client.gateway;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.gateway.client.dao.ClientDAO;
import com.fiap.burger.gateway.client.model.ClientJPA;
import com.fiap.burger.usecase.adapter.gateway.ClientCpfGateway;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultClientGateway implements ClientGateway {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ClientCpfGateway clientCpfGateway;

    @Override
    public Client findById(Long id) {
        return clientDAO.findById(id).map(ClientJPA::toEntity).orElse(null);
    }

    @Override
    public Client findByCpf(String cpf) {
        Optional<Client> client = clientDAO.findByCpf(cpf).map(ClientJPA::toEntity);
        return client.orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        Client persisted = clientDAO.save(ClientJPA.toJPA(client)).toEntity();
        clientCpfGateway.save(persisted.getCpf(), persisted.getId());
        return persisted;
    }

}
