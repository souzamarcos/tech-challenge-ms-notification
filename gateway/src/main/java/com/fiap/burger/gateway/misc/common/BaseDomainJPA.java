package com.fiap.burger.gateway.misc.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDomainJPA extends AuditableJPA {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }
}
