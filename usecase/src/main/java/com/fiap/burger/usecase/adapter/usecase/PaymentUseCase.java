package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.payment.Payment;


public interface PaymentUseCase {
    Payment findById(Long id);
}
