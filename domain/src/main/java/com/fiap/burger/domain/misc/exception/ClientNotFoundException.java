package com.fiap.burger.domain.misc.exception;

public class ClientNotFoundException extends NotFoundException {

    public ClientNotFoundException() {
        super("Client not found");
    }
}
