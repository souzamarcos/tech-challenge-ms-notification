package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;

import java.util.List;


public interface PaymentUseCase {
    Payment findById(Long id);

    List<Payment> findByOrderId(Long orderId);

    Payment insert(Long orderId);

    Payment updateStatus(Long id, PaymentStatus status);
}
