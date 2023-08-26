package com.fiap.burger.api.dto.order.response;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;
import java.util.stream.Collectors;

public record OrderResponseDto(

        @NotNull
        Long id,

        @Null
        OrderClientResponseDto client,

        @NotNull
        List<OrderItemResponseDto> items,

        @NotNull
        Double total,

        @NotNull
        OrderStatus status
) {

    public static OrderResponseDto toResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                OrderClientResponseDto.toResponseDto(order.getClient()),
                order.getItems().stream().map(OrderItemResponseDto::toResponseDto).collect(Collectors.toList()),
                order.getTotal(),
                order.getStatus()
        );
    }
}