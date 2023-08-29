package com.fiap.burger.usecase.misc.exception;

public class OrderNotFoundException extends NotFoundException {

    public OrderNotFoundException() {
        super("Order not found");
    }

    public OrderNotFoundException(Long orderId) {
        super("Order '" + orderId + "' not found");
    }
}
