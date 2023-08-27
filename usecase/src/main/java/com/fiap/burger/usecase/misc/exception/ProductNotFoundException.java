package com.fiap.burger.usecase.misc.exception;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(Long productId) {
        super("Product '" + productId + "' not found");
    }
}
