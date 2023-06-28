package com.fiap.burger.persistence.order.model;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.persistence.client.model.ClientJPA;
import com.fiap.burger.persistence.misc.common.BaseDomainJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import org.aspectj.weaver.ast.Or;

@Entity
@Table(name = "`order`")
public class OrderJPA extends BaseDomainJPA {

    @ManyToOne
    ClientJPA client;

//    @OneToMany
//    Set<OrderModelItemJPA> items;
    @Column
    Double total;
    @Enumerated(EnumType.STRING)
    @Column
    OrderStatus status;

    public OrderJPA() {}

    public OrderJPA(
            Long id,
            ClientJPA client,
//            Set<OrderModelItemJPA> items,
            Double total,
            OrderStatus status,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime deletedAt

    ) {
        this.id = id;
        this.client = client;
        this.total = total;
//        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Order toEntity() {
        return new Order(id, client.getId(), total, status, createdAt, modifiedAt, deletedAt);
    }
}
