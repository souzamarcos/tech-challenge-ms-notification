package com.fiap.burger.usecase.misc.exception;

public class OrderAlreadyPaidException extends DomainException {

    public OrderAlreadyPaidException() {
        super("Order has already been paid");
    }

    public OrderAlreadyPaidException(Long orderId) {
        super("Order '" + orderId + "' has already been paid");
    }
}
