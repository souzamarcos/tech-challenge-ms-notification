package com.fiap.burger.domain.misc.exception;

public class OrderNotFoundException extends NotFoundException {

    public OrderNotFoundException() {
        super("Order not found");
    }

    public OrderNotFoundException(Long orderId) {
        super("Order '" + orderId + "' not found");
    }
}
