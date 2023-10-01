package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;

import java.util.List;

public interface PaymentController {
    Payment findById(Long id);

    List<Payment> findByOrderId(Long orderId);

    Payment insert(Long orderId);

    void updateStatus(Long id, PaymentStatus status);
}
