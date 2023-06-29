package com.fiap.burger.web.dto.order.request;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.entities.product.Category;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

public record OrderInsertRequestDto (
    @Null
    Long clientId

    //TODO implementar recebimento de items no dto para inserir junto com o pedido
    //    List<OrderItemInsertRequestDto> items,
) {
    public Order toEntity() {
        return new Order(clientId, OrderStatus.RECEBIDO);
    }
}
