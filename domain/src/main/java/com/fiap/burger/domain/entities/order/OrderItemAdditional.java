package com.fiap.burger.domain.entities.order;

import java.util.Objects;

public class OrderItemAdditional {
    Long id;

    Long orderItemId;
    Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemAdditional orderItemAdditional)) return false;
        return Objects.equals(hashCode(), orderItemAdditional.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getOrderItemId(),
                getProductId()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderItemAdditional(Long id, Long orderItemId, Long productId) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.productId = productId;
    }

    public OrderItemAdditional(Long orderItemId, Long productId) {
        this.orderItemId = orderItemId;
        this.productId = productId;
    }
}
