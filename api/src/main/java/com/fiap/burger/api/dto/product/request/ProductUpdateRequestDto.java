package com.fiap.burger.api.dto.product.request;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequestDto(
        @NotNull Long id,
        @NotBlank Category category,
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank
        @DecimalMin("0.01")
        Double value
) {
    public Product toEntity() {
        return new Product(id, category, name, description, value);
    }
}
