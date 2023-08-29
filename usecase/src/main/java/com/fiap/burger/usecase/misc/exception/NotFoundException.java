package com.fiap.burger.usecase.misc.exception;

public abstract class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super(message);
    }
}
