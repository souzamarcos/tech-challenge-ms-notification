package com.fiap.burger.usecase.adapter.gateway;


public interface ClientCpfGateway {

    void save(String cpf, Long clientId);

    Long findByCpf(String cpf);

}
