package com.fiap.burger.messenger.payment;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;

public class PaymentMessageDto {
    private Long id;
    private Long orderId;
    private PaymentStatus status;

    public static PaymentMessageDto toDto(Payment payment) {
        return new PaymentMessageDto(payment.getId(), payment.getOrderId(), payment.getStatus());
    }

    public PaymentMessageDto(Long id, Long orderId, PaymentStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }
}
