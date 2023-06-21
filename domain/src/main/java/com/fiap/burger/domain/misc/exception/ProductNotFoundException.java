package com.fiap.burger.domain.misc.exception;

public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException() {
        super("Product not found");
    }
}
