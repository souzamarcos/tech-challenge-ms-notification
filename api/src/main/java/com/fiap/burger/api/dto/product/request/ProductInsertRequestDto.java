package com.fiap.burger.api.dto.product.request;

import com.fiap.burger.entity.entity.product.Category;
import com.fiap.burger.entity.entity.product.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record ProductInsertRequestDto(
        @NotBlank Category category,
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank
        @DecimalMin("0.01")
        Double value
) {
    public Product toEntity() {
        return new Product(category, name, description, value);
    }
}
