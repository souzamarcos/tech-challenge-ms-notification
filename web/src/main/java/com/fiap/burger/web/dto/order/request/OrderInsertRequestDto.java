package com.fiap.burger.web.dto.order.request;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.entities.product.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.stream.Collectors;

public record OrderInsertRequestDto (
    Long clientId,
    List<OrderItemInsertRequestDto> items,
    OrderStatus status
) {
    public Order toEntity() {
        return new Order(clientId, items.stream().map(OrderItemInsertRequestDto::toEntity).collect(Collectors.toList()), status);
    }
}
