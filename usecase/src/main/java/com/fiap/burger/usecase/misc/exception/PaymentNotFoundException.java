package com.fiap.burger.usecase.misc.exception;

public class PaymentNotFoundException extends NotFoundException {

    public PaymentNotFoundException() {
        super("Payment not found");
    }

    public PaymentNotFoundException(Long id) {
        super("Payment '" + id + "' not found");
    }
}
