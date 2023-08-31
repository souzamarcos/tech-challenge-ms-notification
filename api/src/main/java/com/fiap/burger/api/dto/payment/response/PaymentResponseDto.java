package com.fiap.burger.api.dto.payment.response;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record PaymentResponseDto(

        @NotNull
        Long id,
        @NotNull
        Long orderId,

        @NotNull
        PaymentStatus status,

        @Null
        String qrCode,

        @Null
        String externalId
) {

    public static PaymentResponseDto toResponseDto(Payment payment) {
        return new PaymentResponseDto(
                payment.getId(),
                payment.getOrderId(),
                payment.getStatus(),
                payment.getQrCode(),
                payment.getExternalId()
        );
    }

}
