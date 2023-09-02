package com.fiap.burger.api.dto.payment.request;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record PaymentInsertRequestDto(
    @NotBlank
    Order orderId



) {
    public Payment toEntity() {
        return new Payment(orderId);
    }
}
