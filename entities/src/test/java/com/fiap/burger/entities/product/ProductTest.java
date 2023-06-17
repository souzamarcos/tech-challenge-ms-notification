package com.fiap.burger.entities.product;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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