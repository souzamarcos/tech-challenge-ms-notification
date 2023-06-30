package com.fiap.burger.web.dto.order.response;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record ListOrderResponseDto(

    @NotNull
    Long id,

    @Null
    OrderClientResponseDto client,

    @NotNull
    Double total,

    @NotNull
    OrderStatus status
) {

    public static ListOrderResponseDto toResponseDto(Order order) {
        return new ListOrderResponseDto(
            order.getId(),
            OrderClientResponseDto.toResponseDto(order.getClient()),
            order.getTotal(),
            order.getStatus()
        );
    }
}
