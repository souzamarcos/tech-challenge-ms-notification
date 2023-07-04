package com.fiap.burger.web.dto.order.request;

import com.fiap.burger.domain.entities.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateStatusRequestDto(
    @NotNull
    OrderStatus newStatus
) {
}
