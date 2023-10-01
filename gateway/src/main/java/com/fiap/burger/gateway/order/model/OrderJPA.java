package com.fiap.burger.gateway.order.model;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.gateway.client.model.ClientJPA;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import com.fiap.burger.gateway.payment.model.PaymentJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "`order`")
public class OrderJPA extends BaseDomainJPA {

    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    ClientJPA client;

    // TODO melhorar perfomance do fetch
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<OrderItemJPA> items;

    @OneToMany(mappedBy = "orderJPA", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<PaymentJPA> payments;
    @Column
    Double total;
    @Enumerated(EnumType.ORDINAL)
    @Column
    OrderStatus status;

    @Column(name = "client_id")
    Long clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderJPA orderJPA = (OrderJPA) o;
        return Objects.equals(client, orderJPA.client) && Objects.equals(items, orderJPA.items) && Objects.equals(payments, orderJPA.payments) && Objects.equals(total, orderJPA.total) && status == orderJPA.status && Objects.equals(clientId, orderJPA.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, items, payments, total, status, clientId);
    }

    public OrderJPA() {
    }

    public OrderJPA(
        Long id,
        Long clientId,
        List<OrderItemJPA> items,
        List<PaymentJPA> payments,
        Double total,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt

    ) {
        this.id = id;
        this.clientId = clientId;
        this.total = total;
        this.items = items;
        this.payments = payments;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Order toEntity() {
        return new Order(
            id,
            Optional.ofNullable(client).map(ClientJPA::toEntity).orElse(null),
            total,
            status,
            createdAt,
            modifiedAt,
            deletedAt
        );
    }

    public Order toEntityWithItems() {
        return new Order(
            id,
            Optional.ofNullable(client).map(ClientJPA::toEntity).orElse(null),
            items.stream().map(OrderItemJPA::toEntityWithAdditional).collect(Collectors.toList()),
            total,
            status,
            createdAt,
            modifiedAt,
            deletedAt
        );
    }

    public Order toEntityWithItemsAndPayments() {
        return new Order(
            id,
            Optional.ofNullable(client).map(ClientJPA::toEntity).orElse(null),
            items.stream().map(OrderItemJPA::toEntityWithAdditional).collect(Collectors.toList()),
            payments.stream().map(PaymentJPA::toEntity).collect(Collectors.toList()),
            total,
            status,
            createdAt,
            modifiedAt,
            deletedAt
        );
    }

    public static OrderJPA toJPA(Order order) {
        OrderJPA newOrder = new OrderJPA(
            order.getId(),
            order.getClientId(),
            null,
            null,
            order.getTotal(),
            order.getStatus(),
            order.getCreatedAt(),
            order.getModifiedAt(),
            order.getDeletedAt()
        );

        if (!Optional.ofNullable(order.getItems()).orElse(Collections.emptyList()).isEmpty()) {
            List<OrderItemJPA> items = order.getItems().stream().map(orderItem -> OrderItemJPA.toJPA(orderItem, newOrder)).collect(Collectors.toList());
            newOrder.setItems(items);
        }
        return newOrder;
    }

    public void setItems(List<OrderItemJPA> items) {
        this.items = items;
    }
}