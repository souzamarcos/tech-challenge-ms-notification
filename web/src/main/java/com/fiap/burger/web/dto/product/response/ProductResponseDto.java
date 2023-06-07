package com.fiap.burger.web.dto.product.response;

import com.fiap.burger.entities.product.Category;
import com.fiap.burger.entities.product.Product;

public record ProductResponseDto(Long id, Category category, String name, String description, Double value) {
    public static ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
            product.id(),
            product.category(),
            product.name(),
            product.description(),
            product.value()
        );
    }
}