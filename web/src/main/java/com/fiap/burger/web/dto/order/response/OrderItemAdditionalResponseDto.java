package com.fiap.burger.web.dto.order.response;


import com.fiap.burger.domain.entities.order.OrderItemAdditional;

public record OrderItemAdditionalResponseDto (
        Long productId,
        String productName
) {
    public static OrderItemAdditionalResponseDto toResponseDto(OrderItemAdditional additional) {
        return new OrderItemAdditionalResponseDto(
                additional.getProduct().getId(),
                additional.getProduct().getName()
        );
    }
}
