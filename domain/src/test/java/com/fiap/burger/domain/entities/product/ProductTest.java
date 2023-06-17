package com.fiap.burger.domain.entities.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    public void shouldCreateStance() {
        Product actual = new Product(
            1L,
            Category.LANCHE,
            "Product Test",
            "Product description",
            22.2
        );

        Product expected = new Product(
            1L,
            Category.LANCHE,
            "Product Test",
            "Product description",
            22.2
        );

        assertEquals(expected, actual);
    }
}