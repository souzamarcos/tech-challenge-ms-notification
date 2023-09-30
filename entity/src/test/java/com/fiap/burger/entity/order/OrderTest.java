package com.fiap.burger.entity.order;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void shouldCreateInstanceToCheckout() {
        var clientId = 1L;
        var items = List.of(new OrderItem(1L, 1L, ""));
        var status = OrderStatus.AGUARDANDO_PAGAMENTO;

        Order actual = new Order(
            clientId,
            items,
            status
        );

        assertEquals(clientId, actual.getClientId());
        assertEquals(items, actual.getItems());
        assertEquals(status, actual.getStatus());
    }

    @Test
    public void shouldCreateInstanceWithSimpleConstructor() {
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
    public void shouldCreateInstanceWithItems() {
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
    public void shouldCreateInstanceWithItemsAndPayments() {
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
