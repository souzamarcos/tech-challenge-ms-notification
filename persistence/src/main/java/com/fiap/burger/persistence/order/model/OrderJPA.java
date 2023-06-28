package com.fiap.burger.persistence.order.model;

import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.persistence.misc.common.BaseDomainJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "order")
public class OrderJPA extends BaseDomainJPA {

    @Column()
    Long clientId;
    @ManyToMany
    Set<OrderModelItemJPA> items;
    @Column()
    Double total;
    @Enumerated(EnumType.STRING)
    @Column()
    OrderStatus status;

    public OrderJPA() {

    }

    public OrderJPA(
            Long id,
            Long clientId,
            Set<OrderModelItemJPA> items,
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
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }


}
