package com.fiap.burger.api.dto.order.request;

import com.fiap.burger.entity.entity.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateStatusRequestDto(
        @NotNull
        OrderStatus newStatus
) {
}
