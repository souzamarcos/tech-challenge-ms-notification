package com.fiap.burger.domain.misc.exception;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(Long productId) {
        super("Product '" + productId + "' not found");
    }
}
