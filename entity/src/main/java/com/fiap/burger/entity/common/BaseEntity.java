package com.fiap.burger.entity.common;

public abstract class BaseEntity extends AuditableEntity {
    protected Long id;

    public Long getId() {
        return id;
    }
}