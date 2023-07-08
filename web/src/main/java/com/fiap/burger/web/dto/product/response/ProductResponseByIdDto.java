package com.fiap.burger.web.dto.product.response;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.time.LocalDateTime;

public record ProductResponseByIdDto(
    @NotBlank Long id,
    @NotBlank Category category,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank
    @DecimalMin("0.01")
    Double value,
    @NotNull
    LocalDateTime createdAt,
    @NotNull
    LocalDateTime modifiedAt,
    @Null
    LocalDateTime deletedAt
) {
    public static ProductResponseByIdDto toResponseDto(Product product) {
        return new ProductResponseByIdDto(
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