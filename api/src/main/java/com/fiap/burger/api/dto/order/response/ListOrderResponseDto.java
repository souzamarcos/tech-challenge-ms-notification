package com.fiap.burger.api.dto.order.response;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record ListOrderResponseDto(

        @NotNull
        Long id,

        @Null
        OrderClientResponseDto client,

        @NotNull
        Double total,

        @NotNull
        OrderStatus status,

        LocalDateTime modifiedAt
) {

    public static ListOrderResponseDto toResponseDto(Order order) {
        return new ListOrderResponseDto(
                order.getId(),
                OrderClientResponseDto.toResponseDto(order.getClient()),
                order.getTotal(),
                order.getStatus(),
                order.getModifiedAt()
        );
    }
}
