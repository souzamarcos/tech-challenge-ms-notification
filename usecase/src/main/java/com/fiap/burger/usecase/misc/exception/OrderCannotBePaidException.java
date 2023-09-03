package com.fiap.burger.usecase.misc.exception;

public class OrderCannotBePaidException extends DomainException {

    public OrderCannotBePaidException() {
        super("The order has already been paid or has been refused.");
    }

    public OrderCannotBePaidException(Long orderId) {
        super("Order '" + orderId + "' has already been paid or has been refused.");
    }
}
