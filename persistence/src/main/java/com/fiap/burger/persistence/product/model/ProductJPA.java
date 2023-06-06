package com.fiap.burger.persistence.product.model;

import com.fiap.burger.entities.product.Product;
import com.fiap.burger.entities.product.Category;
import jakarta.persistence.*;

@Entity(name = "product")
public record ProductJPA(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Category category,
    @Column(nullable = false)
    String name,
    @Column(nullable = false)
    String description
) {

    public Product toEntity(){
        return new Product(id, category, name, description);
    }
}

