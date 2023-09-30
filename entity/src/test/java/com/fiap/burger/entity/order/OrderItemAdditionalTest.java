package com.fiap.burger.entity.order;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemAdditionalTest {

    @Test
    public void shouldCreateInstanceWithoutId() {
        var orderItemId = 1L;
        var productId = 1L;

        OrderItemAdditional actual = new OrderItemAdditional(
            orderItemId,
            productId
        );

        assertEquals(orderItemId, actual.getOrderItemId());
        assertEquals(productId, actual.getProductId());
    }

    @Test
    public void shouldCreateInstanceWithId() {
        var id = 1L;
        var orderItemId = 1L;
        var productId = 1L;

        OrderItemAdditional actual = new OrderItemAdditional(
            id,
            orderItemId,
            productId
        );

        assertEquals(id, actual.getId());
        assertEquals(orderItemId, actual.getOrderItemId());
        assertEquals(productId, actual.getProductId());
    }

    @Test
    public void shouldCreateInstanceWithIdAndProduct() {
        var id = 1L;
        var orderItemId = 1L;
        var product = new Product(1L, Category.LANCHE, "Nome", "Descrição", 1.0);

        OrderItemAdditional actual = new OrderItemAdditional(
            id,
            orderItemId,
            product
        );

        assertEquals(id, actual.getId());
        assertEquals(orderItemId, actual.getOrderItemId());
        assertEquals(product, actual.getProduct());
    }
}
