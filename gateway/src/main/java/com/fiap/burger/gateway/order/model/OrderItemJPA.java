package com.fiap.burger.gateway.order.model;

import com.fiap.burger.entity.order.OrderItem;
import com.fiap.burger.gateway.product.model.ProductJPA;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_item")
public class OrderItemJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "order_id", insertable = true, updatable = false)
    @ManyToOne(optional = false)
    OrderJPA order;

    @Column(name = "order_id", insertable = false, updatable = false)
    Long orderId;

    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    ProductJPA product;

    @Column(name = "product_id")
    Long productId;

    @Column
    String comment;

    @OneToMany(mappedBy = "orderItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<OrderItemAdditionalJPA> orderItemAdditional;

    public OrderItemJPA() {

    }

    public OrderItemJPA(
        Long productId,
        String comment,
        OrderJPA orderJPA
    ) {
        this.productId = productId;
        this.comment = comment;
        this.order = orderJPA;
    }

    public OrderItem toEntity() {
        return new OrderItem(
            id,
            orderId,
            productId,
            null,
            comment
        );
    }

    public OrderItem toEntityWithAdditional() {
        return new OrderItem(
            id,
            orderId,
            Optional.ofNullable(orderItemAdditional).map(items -> items.stream().map(OrderItemAdditionalJPA::toEntity).collect(Collectors.toList())).orElse(null),
            comment,
            Optional.ofNullable(product).map(ProductJPA::toEntity).orElse(null)
        );
    }

    public static OrderItemJPA toJPA(OrderItem orderItem, OrderJPA orderJPA) {
        OrderItemJPA newOrderItem = new OrderItemJPA(
            orderItem.getProductId(),
            orderItem.getComment(),
            orderJPA
        );

        if (!Optional.ofNullable(orderItem.getAdditionalIds()).orElse(Collections.emptyList()).isEmpty()) {
            List<OrderItemAdditionalJPA> itemAdditionals = orderItem.getAdditionalIds().stream().map(itemAdditional -> OrderItemAdditionalJPA.toJPA(itemAdditional, newOrderItem)).collect(Collectors.toList());
            newOrderItem.setOrderItemAdditional(itemAdditionals);
        }
        return newOrderItem;
    }

    public void setOrderItemAdditional(List<OrderItemAdditionalJPA> orderItemAdditional) {
        this.orderItemAdditional = orderItemAdditional;
    }
}

