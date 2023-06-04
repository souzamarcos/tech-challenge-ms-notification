package com.fiap.burger.entities.item;

public record Item(
    Long id,
    ItemType type,
    String name,
    String description
) {}
