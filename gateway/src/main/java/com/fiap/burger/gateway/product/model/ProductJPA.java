package com.fiap.burger.gateway.product.model;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "product")
@Entity
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

    public ProductJPA() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(hashCode(), product.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getCategory(),
                getName(),
                getDescription(),
                getValue(),
                getCreatedAt(),
                getModifiedAt(),
                getDeletedAt()
        );
    }

    public static ProductJPA toJPA(Product product) {
        return new ProductJPA(
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

    public Product toEntity() {
        return new Product(id, category, name, description, value, createdAt, modifiedAt, deletedAt);
    }
}