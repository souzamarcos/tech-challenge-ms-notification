package com.fiap.burger.gateway.misc;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;

import java.time.LocalDateTime;

public class ProductBuilder {

    private Long id = 1L;
    private Category category = Category.LANCHE;
    private String name = "Lanche de teste";
    private String description = "Lanche bom";
    private Double value = 20.22;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();
    private LocalDateTime deletedAt = null;

    public ProductBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withValue(Double value) {
        this.value = value;
        return this;
    }

    public ProductBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ProductBuilder withModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public ProductBuilder withDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Product build() {
        return new Product(id, category, name, description, value, createdAt, modifiedAt, deletedAt);
    }
}

