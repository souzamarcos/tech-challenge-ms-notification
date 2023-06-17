package com.fiap.burger.persistence.product.model;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.entities.product.Category;
import jakarta.persistence.*;

@Entity(name = "product")
public class ProductJPA {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
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

    public ProductJPA(Long id, Category category, String name, String description, Double value) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.value = value;
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
        return new Product(id, category, name, description, value);
    }
}

