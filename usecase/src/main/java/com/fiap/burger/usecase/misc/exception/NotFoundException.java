package com.fiap.burger.usecase.misc.exception;

public abstract class NotFoundException extends DomainException {

    protected NotFoundException(String message) {
        super(message);
    }
}
