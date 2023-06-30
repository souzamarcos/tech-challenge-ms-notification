package com.fiap.burger.persistence.order.model;

import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.persistence.product.model.ProductJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItemJPA {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    OrderJPA order;

    @Column(name = "order_id")
    Long orderId;

    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    ProductJPA product;

    @Column(name = "product_id")
    Long productId;

    //    @Column
    //    List<Long> additionalIds;
    @Column
    String comment;

    public OrderItemJPA() {

    }

    public OrderItemJPA(
        Long id,
        Long orderId,
        Long productId,
//        List<Long> additionalIds,
        String comment
    ) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
//        this.additionalIds = additionalIds;
        this.comment = comment;
    }

    public OrderItem toEntity() {
        return new OrderItem(
            id,
            orderId,
            productId,
            comment
        );
    }

    public static OrderItemJPA toJPA(OrderItem orderItem) {
        return new OrderItemJPA(
            orderItem.getId(),
            orderItem.getOrderId(),
            orderItem.getProductId(),
            orderItem.getComment()
        );
    }
}
