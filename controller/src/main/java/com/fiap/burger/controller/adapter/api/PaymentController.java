package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.payment.Payment;

import java.util.List;

public interface PaymentController {
    Payment findById(Long id);
    List<Payment> findByOrderId(Long orderId);
    Payment insert(Long orderId);
}
