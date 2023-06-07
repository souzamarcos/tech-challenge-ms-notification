package com.fiap.burger.persistence.misc.extension;

import com.fiap.burger.entities.product.Product;
import com.fiap.burger.persistence.product.model.ProductJPA;

public class ProductExtension {
    public static ProductJPA toJPA(Product product) {
        return new ProductJPA(
            product.id(),
            product.category(),
            product.name(),
            product.description(),
            product.value()
        );
    }
}
