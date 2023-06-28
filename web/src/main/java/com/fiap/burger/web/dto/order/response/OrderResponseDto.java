package com.fiap.burger.web.dto.order.response;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.web.dto.order.request.OrderItemInsertRequestDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
public record OrderResponseDto(
    @Null
    OrderClientResponseDto client,

    @NotNull
    Double total,

    @NotNull
    OrderStatus status
) {

    public static OrderResponseDto toResponseDto(Order order) {
        return new OrderResponseDto(
            OrderClientResponseDto.toResponseDto(order.getClient()),
            order.getTotal(),
            order.getStatus()
        );
    }
}
