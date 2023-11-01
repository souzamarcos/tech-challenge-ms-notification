package com.fiap.burger.gateway.misc;

import com.fiap.burger.gateway.client.model.ClientJPA;
import com.fiap.burger.gateway.clientcpf.model.ClientCpfModel;
import java.time.LocalDateTime;

public class ClientCpfModelBuilder {

    private Long id = 1L;

    private String cpf = "68203895077";

    public ClientCpfModelBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ClientCpfModelBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClientCpfModel build() {
        return new ClientCpfModel(cpf, id);
    }

}
