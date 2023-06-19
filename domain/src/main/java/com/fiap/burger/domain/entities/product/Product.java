package com.fiap.burger.domain.entities.product;

import com.fiap.burger.domain.entities.common.BaseEntity;
import java.time.LocalDateTime;

public class Product extends BaseEntity {
    private Category category;
    private String name;
    private String description;
    private Double value;

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public Product(Category category, String name, String description, Double value) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public Product(
        Long id,
        Category category,
        String name,
        String description,
        Double value,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt
    ) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.value = value;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }
}
