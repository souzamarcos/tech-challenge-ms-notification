package com.fiap.burger.domain.entities.order;

import java.util.List;
import java.util.Objects;

public class OrderItem {

    Long id;

    Long orderId;


    Long productId;
    //    List<Long> additionalIds;
    String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(hashCode(), orderItem.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getOrderId(),
            getProductId(),
            getComment()
        );
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

//    public List<Long> getAdditionalIds() {
//        return additionalIds;
//    }

    public String getComment() {
        return comment;
    }

    public OrderItem(Long id, Long orderId, Long productId, String comment) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
//        this.additionalIds = additionalIds;
        this.comment = comment;
    }

    public OrderItem(Long orderId, Long productId, String comment) {
        this.orderId = orderId;
        this.productId = productId;
        this.comment = comment;
    }
}
