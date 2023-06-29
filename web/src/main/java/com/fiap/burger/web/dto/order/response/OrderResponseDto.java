package com.fiap.burger.web.dto.order.response;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
public record OrderResponseDto(

    @NotNull
    Long id,

    @Null
    OrderClientResponseDto client,

    //TODO implementar items do pedido

    @NotNull
    Double total,

    @NotNull
    OrderStatus status
) {

    public static OrderResponseDto toResponseDto(Order order) {
        return new OrderResponseDto(
            order.getId(),
            OrderClientResponseDto.toResponseDto(order.getClient()),
            order.getTotal(),
            order.getStatus()
        );
    }
}
