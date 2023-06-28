package com.fiap.burger.web.dto.order.request;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.entities.product.Category;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public record OrderInsertRequestDto (
    @NotNull
    Long clientId,
    List<OrderItemInsertRequestDto> items,
    @NotNull
    OrderStatus status
) {
    public Order toEntity() {
        return new Order(clientId, status);
    }
}
