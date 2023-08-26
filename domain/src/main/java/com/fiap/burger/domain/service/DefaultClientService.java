package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.service.ClientService;
import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.misc.exception.ClientCpfAlreadyExistsException;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import static com.fiap.burger.domain.misc.ValidationCPF.validateCPF;
import static com.fiap.burger.domain.misc.ValidationUtils.*;

@Service
public class DefaultClientService implements ClientService {

    public Client findById(ClientRepository repository, Long id) {return repository.findById(id);}

    public Client findByCpf(ClientRepository repository, String cpf) {return repository.findByCpf(cpf);}

    public Client insert(ClientRepository repository, Client client) {
        Client persistedClient = findByCpf(repository, client.getCpf());
        if(persistedClient != null) {
            throw new ClientCpfAlreadyExistsException();
        }
        validateClientToInsert(client);
        return repository.save(client);
    }

    private void validateClientToInsert(Client client) {
        if(client.getId() != null) throw new InvalidAttributeException("Client should not have defined id", "id");
        validateClient(client);
    }

    private void validateClient(Client client) {
        validateNotNull(client.getCpf(), "cpf");
        validateNotBlank(client.getCpf(), "cpf");
        validateCPF(client.getCpf());
        validateNotNull(client.getEmail(), "email");
        validateNotBlank(client.getEmail(), "email");
        validateEmailFormat(client.getEmail(), "email");
        validateNotNull(client.getName(), "name");
        validateNotBlank(client.getName(), "name");
    }
}
