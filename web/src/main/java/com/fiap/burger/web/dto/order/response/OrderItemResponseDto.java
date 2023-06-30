package com.fiap.burger.web.dto.order.response;

import com.fiap.burger.domain.entities.order.OrderItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record OrderItemResponseDto(
    @NotNull
    Long productId,
    //TODO implementar additionals
//    List<Long> additionalIds,
    @Null
    String comment
) {
    public static OrderItemResponseDto toResponseDto(OrderItem order) {
        return new OrderItemResponseDto(
            order.getProductId(),
            order.getComment()
        );
    }
}