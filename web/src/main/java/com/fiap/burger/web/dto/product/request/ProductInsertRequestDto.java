package com.fiap.burger.web.dto.product.request;

import com.fiap.burger.entities.product.Category;
import com.fiap.burger.entities.product.Product;

public record ProductInsertRequestDto(Category category, String name, String description, Double value) {
    public Product toEntity() {
        return new Product(null, category, name, description, value);
    }
}