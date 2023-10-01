package com.fiap.burger.api.dto.payment.request;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import jakarta.validation.constraints.NotBlank;

public record PaymentWebhookRequestDto(

    @NotBlank
    Long id,

    @NotBlank
    PaymentStatus status
) {

    public Payment toEntity() {
        return new Payment(id, status);
    }
}
