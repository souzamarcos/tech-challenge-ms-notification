package com.fiap.burger.api.dto.payment.request;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentWebhookRequestDto (

    @NotBlank
    Long id,

    @NotBlank
    PaymentStatus status
){

    public Payment toEntity() {
        return new Payment(id, status);
    }
}
