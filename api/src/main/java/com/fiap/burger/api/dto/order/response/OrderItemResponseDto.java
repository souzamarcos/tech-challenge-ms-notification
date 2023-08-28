package com.fiap.burger.api.dto.order.response;

import com.fiap.burger.entity.order.OrderItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;
import java.util.stream.Collectors;

public record OrderItemResponseDto(
        @NotNull
        Long productId,

        String productName,

        @Null
        String comment,

        List<OrderItemAdditionalResponseDto> additionals
) {
    public static OrderItemResponseDto toResponseDto(OrderItem order) {
        return new OrderItemResponseDto(
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getComment(),
                order.getOrderItemAdditionals().stream().map(OrderItemAdditionalResponseDto::toResponseDto).collect(Collectors.toList())
        );
    }
}