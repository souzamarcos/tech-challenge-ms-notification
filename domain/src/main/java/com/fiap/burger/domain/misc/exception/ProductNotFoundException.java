package com.fiap.burger.domain.misc.exception;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        super("Product not found");
    }
}
