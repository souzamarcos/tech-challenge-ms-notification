package com.fiap.burger.usecase.misc.exception;

public class InvalidCPFException extends DomainException {

    public InvalidCPFException() {
        super("Invalid CPF.");
    }

}

