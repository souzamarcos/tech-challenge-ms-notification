package com.fiap.burger.api.dto.payment.request;

import jakarta.validation.constraints.NotBlank;

public record PaymentInsertRequestDto(
    @NotBlank
    Long orderId
) {
}
