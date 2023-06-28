package com.fiap.burger.domain.entities.order;

import java.util.List;
import java.util.Objects;

public class OrderItem {
    Long productId;
    List<Long> additionalIds;
    String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(hashCode(), orderItem.hashCode());     }

    @Override
    public int hashCode() {
        return Objects.hash(
                getProductId(),
                getAdditionalIds(),
                getComment()
        );
    }

    public Long getProductId() {
        return productId;
    }

    public List<Long> getAdditionalIds() {
        return additionalIds;
    }

    public String getComment() {
        return comment;
    }

    public OrderItem(Long productId, List<Long> additionalIds, String comment) {
        this.productId = productId;
        this.additionalIds = additionalIds;
        this.comment = comment;
    }
}
