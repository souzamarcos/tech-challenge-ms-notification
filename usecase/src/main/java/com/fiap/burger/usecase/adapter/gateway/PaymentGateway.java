package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentGateway {
    Payment findById(Long id);

    List<Payment> findByOrderId(Long orderId);

    Payment save(Payment payment);

    void updatePaymentStatus(Long id, PaymentStatus status, LocalDateTime modifiedAt);
}
