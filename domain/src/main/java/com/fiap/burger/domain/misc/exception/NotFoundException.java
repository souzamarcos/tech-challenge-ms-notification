package com.fiap.burger.domain.misc.exception;

public abstract class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super(message);
    }
}
