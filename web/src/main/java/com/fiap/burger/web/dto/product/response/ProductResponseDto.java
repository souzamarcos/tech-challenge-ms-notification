package com.fiap.burger.web.dto.product.response;

import com.fiap.burger.entities.product.Category;
import com.fiap.burger.entities.product.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record ProductResponseDto(
    @NotBlank Long id,
    @NotBlank Category category,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank
    @DecimalMin("0.01")
    Double value
) {
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