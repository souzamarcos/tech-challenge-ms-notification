package com.fiap.burger.persistence.order.model;

import com.fiap.burger.domain.entities.order.OrderItemAdditional;
import com.fiap.burger.persistence.product.model.ProductJPA;
import jakarta.persistence.*;

@Entity
@Table(name = "order_item_additional")
public class OrderItemAdditionalJPA {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "order_item_id", insertable = true, updatable = false)
    @ManyToOne(optional = false)
    OrderItemJPA orderItem;
    @Column(name = "order_item_id", insertable = false, updatable=false)
    Long orderItemId;

    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    ProductJPA product;

    @Column(name = "product_id")
    Long productId;


    public OrderItemAdditionalJPA(OrderItemJPA orderItem, Long productId) {
        this.orderItem = orderItem;
        this.productId = productId;
    }

    public OrderItemAdditionalJPA() {

    }

    public OrderItemAdditional toEntity() {
        return  new OrderItemAdditional(
                id,
                orderItemId,
                product.toEntity()
        );
    }


    public static OrderItemAdditionalJPA toJPA(Long productId, OrderItemJPA orderItemJPA) {
        return  new OrderItemAdditionalJPA(
                orderItemJPA,
                productId
        );
    }

}
