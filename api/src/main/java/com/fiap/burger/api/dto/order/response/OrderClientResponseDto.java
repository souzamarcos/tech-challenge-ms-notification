package com.fiap.burger.api.dto.order.response;

import com.fiap.burger.entity.entity.client.Client;
import jakarta.validation.constraints.NotNull;

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
