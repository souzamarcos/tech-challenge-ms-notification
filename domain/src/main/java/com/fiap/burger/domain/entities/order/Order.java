package com.fiap.burger.domain.entities.order;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.entities.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order extends BaseEntity {

    private Long clientId;



    private Client client;

    //    private List<OrderItem> item;
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
//                getItem(),
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

//    public List<OrderItem> getItem() {
//        return item;
//    }

    public Double getTotal() {
        return total;
    }

    private void calculateTotal() {
        // TODO fazer calculo do total
        this.total = 0.0;
    }

    public OrderStatus getStatus() {
        return status;
    }

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

    public Order(Long clientId, OrderStatus status) {
        calculateTotal();
        this.clientId = clientId;
        this.status = status;
    }
}
