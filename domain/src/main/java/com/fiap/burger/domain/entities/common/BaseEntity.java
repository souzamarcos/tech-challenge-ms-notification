package com.fiap.burger.domain.entities.common;

public abstract class BaseEntity extends AuditableEntity {
    protected Long id;

    public Long getId() {
        return id;
    }
}
