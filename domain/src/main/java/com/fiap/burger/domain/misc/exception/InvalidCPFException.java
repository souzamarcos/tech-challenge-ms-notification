package com.fiap.burger.domain.misc.exception;

public class InvalidCPFException extends DomainException {

    public InvalidCPFException() {
        super("CPF must have 11 digits.");
    }

}

