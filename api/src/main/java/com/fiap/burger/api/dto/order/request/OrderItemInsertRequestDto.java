package com.fiap.burger.api.dto.order.request;

import com.fiap.burger.entity.order.OrderItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record OrderItemInsertRequestDto(
    @NotNull
    Long productId,
    @Null
    List<Long> additionalIds,
    @Null
    String comment
) {
    public OrderItem toEntity() {

        return new OrderItem(
            null,
            null,
            productId,
            additionalIds,
            comment
        );
    }
}
