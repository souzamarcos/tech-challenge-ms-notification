package com.fiap.burger.usecase.misc.exception;

public class ClientCpfAlreadyExistsException extends DomainException {

    public ClientCpfAlreadyExistsException() {
        super("Client CPF already exists");
    }

}
