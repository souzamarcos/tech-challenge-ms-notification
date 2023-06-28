package com.fiap.burger.web.dto.order.request;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;

import java.util.List;

public record OrderItemInsertRequestDto(
        Long productId,
        List<Long>additionalIds,
        String comment
) {
    public OrderItem toEntity() {

        return new OrderItem(
                productId,
                additionalIds,
                comment
        );
    }
}
