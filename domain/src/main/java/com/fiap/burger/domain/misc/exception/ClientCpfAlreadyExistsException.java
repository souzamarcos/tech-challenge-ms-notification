package com.fiap.burger.domain.misc.exception;

public class ClientCpfAlreadyExistsException extends DomainException {

    public ClientCpfAlreadyExistsException() {
        super("Client CPF already exists");
    }

}
