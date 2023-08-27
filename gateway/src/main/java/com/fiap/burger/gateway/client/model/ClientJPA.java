package com.fiap.burger.gateway.client.model;

import com.fiap.burger.entity.entity.client.Client;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "client")
public class ClientJPA extends BaseDomainJPA {

    @Column(nullable = false)
    String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    public ClientJPA() {

    }

    public ClientJPA(Long id, String cpf, String email, String name, LocalDateTime createdAt,
                     LocalDateTime modifiedAt,
                     LocalDateTime deletedAt) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(hashCode(), client.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getCpf(),
                getEmail(),
                getName(),
                getCreatedAt(),
                getModifiedAt(),
                getDeletedAt()
        );
    }

    public static ClientJPA toJPA(Client client) {
        if (client == null) return null;
        return new ClientJPA(
                client.getId(),
                client.getCpf(),
                client.getEmail(),
                client.getName(),
                client.getCreatedAt(),
                client.getModifiedAt(),
                client.getDeletedAt()
        );
    }

    public Client toEntity() {
        return new Client(id, cpf, email, name, createdAt, modifiedAt, deletedAt);
    }
}

