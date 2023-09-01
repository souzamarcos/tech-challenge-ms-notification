package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;


public class DefaultPaymentUseCase implements PaymentUseCase {

    private final PaymentGateway paymentGateway;

    public DefaultPaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment findById(Long id) {
        return paymentGateway.findById(id);
    }


}
