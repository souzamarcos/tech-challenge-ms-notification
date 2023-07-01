package com.fiap.burger.web.dto.order.response;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.web.dto.order.request.OrderItemInsertRequestDto;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public record OrderClientResponseDto(
    @NotNull
    Long id,
    String name
) {
    public static OrderClientResponseDto toResponseDto(Client client) {
        return Optional.ofNullable(client)
            .map(c -> new OrderClientResponseDto(c.getId(), c.getName()))
            .orElse(null);
    }
}
