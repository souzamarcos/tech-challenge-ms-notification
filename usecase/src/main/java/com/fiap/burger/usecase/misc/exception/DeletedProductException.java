package com.fiap.burger.usecase.misc.exception;

public class DeletedProductException extends DomainException {

    public DeletedProductException() {
        super("Product already deleted");
    }
}
