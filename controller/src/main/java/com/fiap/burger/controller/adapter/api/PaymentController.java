package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.payment.Payment;

public interface PaymentController {
    Payment findById(Long id);
    Payment insert(Long orderId);

}
