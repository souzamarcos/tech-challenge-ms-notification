package com.fiap.burger.gateway.misc;

import com.fiap.burger.gateway.client.model.ClientJPA;

import java.time.LocalDateTime;

public class ClientJPABuilder {

    private Long id = 1L;

    private String cpf = "68203895077";

    private String email = "email@email.com";

    private String name = "Nome do Cliente";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt = null;

    public ClientJPABuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ClientJPABuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClientJPABuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientJPABuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientJPA build() {
        return new ClientJPA(id, cpf, email, name, createdAt, modifiedAt, deletedAt);
    }

}
