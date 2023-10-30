package com.fiap.burger.entity.order;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void shouldCreateInstanceToCheckout() {
        var clientToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImNsaWVudElkIjoxLCJpc3MiOiJQb3MtVGVjaCBGSUFQIC0gQnVyZ2VyIiwiY3BmIjoiMTY1NjU4MjQ3MzgiLCJpYXQiOjE2OTg2MDI2NzEsImp0aSI6ImE5MTYzZTBjLWM1NzgtNDYxNC04ZWZjLTYwMzNiNTg0Y2FlOCJ9.kX5zh2vyqsYuyqz1Yf-lJtYnDt9jRVyMuls3qC-hBD8";
        var items = List.of(new OrderItem(1L, 1L, ""));
        var status = OrderStatus.AGUARDANDO_PAGAMENTO;

        Order actual = new Order(
            clientToken,
            items,
            status
        );

        assertEquals(clientToken, actual.getClientToken());
        assertEquals(items, actual.getItems());
        assertEquals(status, actual.getStatus());
    }

    @Test
    void shouldCreateInstanceWithSimpleConstructor() {
        var id = 1L;
        var client = new Client(1L);
        var total = 10.0;
        var status = OrderStatus.AGUARDANDO_PAGAMENTO;
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Order actual = new Order(
            id,
            client,
            total,
            status,
            createdAt,
            modifiedAt,
            null
        );

        assertEquals(id, actual.getId());
        assertEquals(client, actual.getClient());
        assertEquals(total, actual.getTotal());
        assertEquals(status, actual.getStatus());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

    @Test
    void shouldCreateInstanceWithItems() {
        var id = 1L;
        var client = new Client(1L);
        var items = List.of(new OrderItem(1L, 1L, ""));
        var total = 10.0;
        var status = OrderStatus.AGUARDANDO_PAGAMENTO;
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Order actual = new Order(
            id,
            client,
            items,
            total,
            status,
            createdAt,
            modifiedAt,
            null
        );

        assertEquals(id, actual.getId());
        assertEquals(client, actual.getClient());
        assertEquals(items, actual.getItems());
        assertEquals(total, actual.getTotal());
        assertEquals(status, actual.getStatus());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

    @Test
    void shouldCreateInstanceWithItemsAndPayments() {
        var id = 1L;
        var client = new Client(1L);
        var items = List.of(new OrderItem(1L, 1L, ""));
        var payments = List.of(new Payment(1L, PaymentStatus.ABERTO));
        var total = 10.0;
        var status = OrderStatus.AGUARDANDO_PAGAMENTO;
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Order actual = new Order(
            id,
            client,
            items,
            payments,
            total,
            status,
            createdAt,
            modifiedAt,
            null
        );

        assertEquals(id, actual.getId());
        assertEquals(client, actual.getClient());
        assertEquals(items, actual.getItems());
        assertEquals(payments, actual.getPayments());
        assertEquals(total, actual.getTotal());
        assertEquals(status, actual.getStatus());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

}
