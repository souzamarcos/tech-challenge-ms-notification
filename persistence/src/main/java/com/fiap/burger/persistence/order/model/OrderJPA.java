package com.fiap.burger.persistence.order.model;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.persistence.client.model.ClientJPA;
import com.fiap.burger.persistence.misc.common.BaseDomainJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "`order`")
public class OrderJPA extends BaseDomainJPA {

    @ManyToOne
    ClientJPA client;

    // TODO melhorar perfomance do fetch
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<OrderItemJPA> items;
    @Column
    Double total;
    @Enumerated(EnumType.STRING)
    @Column
    OrderStatus status;

    public OrderJPA() {
    }

    public OrderJPA(
        Long id,
        ClientJPA client,
        List<OrderItemJPA> items,
        Double total,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt

    ) {
        this.id = id;
        this.client = client;
        this.total = total;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Order toEntity() {
        return new Order(
            id,
            Optional.ofNullable(client).map(ClientJPA::getId).orElse(null),
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
            Optional.ofNullable(client).map(ClientJPA::getId).orElse(null),
            //TODO verificar pq os itens as vezes são retornados e as vezes não
            items.stream().map(OrderItemJPA::toEntityWithAdditional).collect(Collectors.toList()),
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
                ClientJPA.toJPA(order.getClient()),
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
