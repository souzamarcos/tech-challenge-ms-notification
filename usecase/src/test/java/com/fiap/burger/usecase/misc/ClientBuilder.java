package com.fiap.burger.usecase.misc;

import com.fiap.burger.entity.client.Client;

import java.time.LocalDateTime;

public class ClientBuilder {

    private Long id = 1L;

    private String cpf = "68203895077";

    private String email = "email@email.com";

    private String name = "Nome do Cliente";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt = null;

    public ClientBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ClientBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClientBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Client build() {
        return new Client(id, cpf, email, name, createdAt, modifiedAt, deletedAt);
    }

}
