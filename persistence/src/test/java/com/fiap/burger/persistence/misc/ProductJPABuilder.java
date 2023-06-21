package com.fiap.burger.persistence.misc;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.persistence.product.model.ProductJPA;
import java.time.LocalDateTime;

public class ProductJPABuilder {

    private Long id = 1L;
    private Category category = Category.LANCHE;
    private String name = "Lanche de teste";
    private String description = "Lanche bom";
    private Double value = 20.22;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();
    private LocalDateTime deletedAt = null;

    public ProductJPABuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductJPABuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public ProductJPABuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductJPABuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductJPABuilder withValue(Double value) {
        this.value = value;
        return this;
    }

    public ProductJPABuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ProductJPABuilder withModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public ProductJPABuilder withDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public ProductJPA build() {
        return new ProductJPA(id, category, name, description, value, createdAt, modifiedAt, deletedAt);
    }
}
