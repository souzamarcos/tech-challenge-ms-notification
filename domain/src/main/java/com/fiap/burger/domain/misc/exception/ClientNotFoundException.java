package com.fiap.burger.domain.misc.exception;

public class ClientNotFoundException extends DomainException {

    public ClientNotFoundException() {
        super("Client not found");
    }
}
