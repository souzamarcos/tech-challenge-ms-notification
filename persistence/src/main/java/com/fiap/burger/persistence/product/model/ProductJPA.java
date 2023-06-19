package com.fiap.burger.persistence.product.model;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.persistence.misc.common.BaseDomainJPA;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "product")
public class ProductJPA extends BaseDomainJPA {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Category category;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Double value;

    public ProductJPA(){}

    public ProductJPA(
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

    public Long getId() {
        return id;
    }

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

    public Product toEntity(){
        return new Product(id, category, name, description, value, createdAt, modifiedAt, deletedAt);
    }
}

