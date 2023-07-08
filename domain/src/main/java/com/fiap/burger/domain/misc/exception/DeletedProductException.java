package com.fiap.burger.domain.misc.exception;

public class DeletedProductException extends DomainException {

    public DeletedProductException() {
        super("Product already deleted");
    }
}
