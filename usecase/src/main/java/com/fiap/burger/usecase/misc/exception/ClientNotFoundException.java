package com.fiap.burger.usecase.misc.exception;

public class ClientNotFoundException extends NotFoundException {

    public ClientNotFoundException() {
        super("Client not found");
    }

    public ClientNotFoundException(Long clientId) {
        super("Client " + clientId + " not found");
    }
}
