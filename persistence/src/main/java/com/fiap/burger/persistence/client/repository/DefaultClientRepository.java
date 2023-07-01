package com.fiap.burger.persistence.client.repository;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.persistence.client.dao.ClientDAO;
import com.fiap.burger.persistence.client.model.ClientJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultClientRepository implements ClientRepository {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public Client findById(Long id) {
        return clientDAO.findById(id).map(ClientJPA::toEntity).orElse(null);
    }

    @Override
    public Client save(Client client)  {
        return clientDAO.save(ClientJPA.toJPA(client)).toEntity();
    }


}
