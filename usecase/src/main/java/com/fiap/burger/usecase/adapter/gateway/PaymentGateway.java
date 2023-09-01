package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.payment.Payment;

public interface PaymentGateway {
    Payment findById(Long id);
}
