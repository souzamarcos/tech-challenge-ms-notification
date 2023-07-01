package com.fiap.burger.persistence.order.model;

import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.persistence.product.model.ProductJPA;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_item")
public class OrderItemJPA {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "order_id", insertable = true, updatable = false)
    @ManyToOne(optional = false)
    OrderJPA order;

    @Column(name = "order_id", insertable = false, updatable=false)
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

    @OneToMany(mappedBy = "orderItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<OrderItemAdditionalJPA> orderItemAdditional;

    public OrderItemJPA() {

    }

    public OrderItemJPA(
            Long productId,
//        List<Long> additionalIds,
            String comment,
            OrderJPA orderJPA
    ) {
        this.productId = productId;
//        this.additionalIds = additionalIds;
        this.comment = comment;
        this.order = orderJPA;
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
            null,
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

    public static OrderItemJPA toJPA2(OrderItem orderItem, OrderJPA orderJPA) {
        OrderItemJPA newOrderItem = new OrderItemJPA(
                orderItem.getProductId(),
                orderItem.getComment(),
                orderJPA
        );

        List<OrderItemAdditionalJPA> itemAdditionals = orderItem.getAdditionalIds().stream().map(itemAdditional -> OrderItemAdditionalJPA.toJPA2(itemAdditional, newOrderItem)).collect(Collectors.toList());

        newOrderItem.setOrderItemAdditional(itemAdditionals);
        return newOrderItem;
    }

    public void setOrderItemAdditional(List<OrderItemAdditionalJPA> orderItemAdditional) {
        this.orderItemAdditional = orderItemAdditional;
    }
}
