package com.fiap.burger.web.dto.product.request;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
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
        return new Product(null, category, name, description, value);
    }
}