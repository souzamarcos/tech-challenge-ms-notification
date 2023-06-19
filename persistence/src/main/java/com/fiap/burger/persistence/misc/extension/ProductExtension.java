package com.fiap.burger.persistence.misc.extension;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.persistence.product.model.ProductJPA;

public class ProductExtension {
    public static ProductJPA toJPA(Product product) {
        return new ProductJPA(
            product.getId(),
            product.getCategory(),
            product.getName(),
            product.getDescription(),
            product.getValue(),
            product.getCreatedAt(),
            product.getModifiedAt(),
            product.getDeletedAt()
        );
    }
}
