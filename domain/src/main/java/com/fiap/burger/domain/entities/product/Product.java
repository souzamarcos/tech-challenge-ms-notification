package com.fiap.burger.domain.entities.product;

public record Product(Long id, Category category, String name, String description, Double value) {}
