package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.payment.Payment;

import java.util.List;

public interface PaymentGateway {
    Payment findById(Long id);
    List<Payment> findByOrderId(Long orderId);

    Payment save(Payment payment);
}
