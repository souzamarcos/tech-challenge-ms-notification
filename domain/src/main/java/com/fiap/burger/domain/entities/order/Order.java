package com.fiap.burger.domain.entities.order;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.entities.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order extends BaseEntity {

    private Long clientId;

    private Client client;
    private List<OrderItem> items;
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
                getItems(),
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

    public Client getClient() {
        return client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setClient(Client client) { this.client = client; }

    public Order(
        Long id,
        Long clientId,
        Double total,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt
    ) {
        this.id = id;
        this.clientId = clientId;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Order(
        Long id,
        Client client,
        List<OrderItem> items,
        Double total,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt
    ) {
        this.id = id;
        this.client = client;
        this.items = items;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Order(Long clientId, List<OrderItem> items, OrderStatus status) {
        this.clientId = clientId;
        this.items = items;
        this.status = status;
    }
}
