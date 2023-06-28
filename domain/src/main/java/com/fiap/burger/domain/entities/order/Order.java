package com.fiap.burger.domain.entities.order;

import com.fiap.burger.domain.entities.common.BaseEntity;

import java.util.List;
import java.util.Objects;

public class Order extends BaseEntity {
    private Long clientId;
    private List<OrderItem> item;
    private Double total;
    private OrderStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(hashCode(), order.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getClientId(),
                getItem(),
                getTotal(),
                getStatus(),
                getCreatedAt(),
                getModifiedAt(),
                getDeletedAt()
        );
    }

    public Long getClientId() {
        return clientId;
    }

    public List<OrderItem> getItem() {
        return item;
    }

    public Double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order(Long clientId, List<OrderItem> item, OrderStatus status) {
        this.clientId = clientId;
        this.item = item;
        this.status = status;
    }


}
