package com.fiap.burger.entity.order;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    @Test
    void shouldCreateInstanceWithSimpleConstructor() {
        var orderId = 1L;
        var productId = 1L;
        var comment = "Comentário";

        OrderItem actual = new OrderItem(
            orderId,
            productId,
            comment
        );

        assertEquals(orderId, actual.getOrderId());
        assertEquals(productId, actual.getProductId());
        assertEquals(comment, actual.getComment());
    }

    @Test
    void shouldCreateInstanceToCheckOut() {
        var id = 1L;
        var orderId = 1L;
        var productId = 1L;
        var additionalIds = List.of(1L);
        var comment = "Comentário";

        OrderItem actual = new OrderItem(
            id,
            orderId,
            productId,
            additionalIds,
            comment
        );

        assertEquals(id, actual.getId());
        assertEquals(orderId, actual.getOrderId());
        assertEquals(productId, actual.getProductId());
        assertEquals(additionalIds, actual.getAdditionalIds());
        assertEquals(comment, actual.getComment());
    }

    @Test
    void shouldCreateInstanceWithFullConstructor() {
        var id = 1L;
        var orderId = 1L;
        var orderItemAdditionals = List.of(new OrderItemAdditional(1L, 1L, 1L));
        var comment = "Comentário";
        var product = new Product(1L, Category.LANCHE, "Nome do Produto", "Descrição", 1.0);

        OrderItem actual = new OrderItem(
            id,
            orderId,
            orderItemAdditionals,
            comment,
            product
        );

        assertEquals(id, actual.getId());
        assertEquals(orderId, actual.getOrderId());
        assertEquals(orderItemAdditionals, actual.getOrderItemAdditionals());
        assertEquals(comment, actual.getComment());
        assertEquals(product, actual.getProduct());
    }

}
