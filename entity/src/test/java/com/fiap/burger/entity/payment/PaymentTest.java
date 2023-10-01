package com.fiap.burger.entity.payment;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTest {

    @Test
    void shouldCreateInstanceWithIdAndStatus() {
        var id = 1L;
        var status = PaymentStatus.ABERTO;

        Payment actual = new Payment(
            id,
            status
        );

        assertEquals(id, actual.getId());
        assertEquals(status, actual.getStatus());
    }

    @Test
    void shouldCreateInstanceWithOrderAndStatus() {
        var order = new Order(1L, new Client(1L), 1.0, OrderStatus.AGUARDANDO_PAGAMENTO, LocalDateTime.now(), LocalDateTime.now(), null);
        var status = PaymentStatus.ABERTO;

        Payment actual = new Payment(
            order,
            status
        );

        assertEquals(order, actual.getOrder());
        assertEquals(status, actual.getStatus());
    }

    @Test
    void shouldCreateInstanceWithIdOrderAndStatus() {
        var id = 1L;
        var order = new Order(1L, new Client(1L), 1.0, OrderStatus.AGUARDANDO_PAGAMENTO, LocalDateTime.now(), LocalDateTime.now(), null);
        var status = PaymentStatus.ABERTO;

        Payment actual = new Payment(
            id,
            order,
            status
        );

        assertEquals(id, actual.getId());
        assertEquals(order, actual.getOrder());
        assertEquals(status, actual.getStatus());
    }

    @Test
    void shouldCreateInstanceWithAuditableFields() {
        var id = 1L;
        var order = new Order(1L, new Client(1L), 1.0, OrderStatus.AGUARDANDO_PAGAMENTO, LocalDateTime.now(), LocalDateTime.now(), null);
        var status = PaymentStatus.ABERTO;
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Payment actual = new Payment(
            id,
            order,
            status,
            createdAt,
            modifiedAt,
            null
        );

        assertEquals(id, actual.getId());
        assertEquals(order, actual.getOrder());
        assertEquals(status, actual.getStatus());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

    @Test
    void shouldCreateInstanceWithFullConstructor() {
        var id = 1L;
        var order = new Order(1L, new Client(1L), 1.0, OrderStatus.AGUARDANDO_PAGAMENTO, LocalDateTime.now(), LocalDateTime.now(), null);
        var status = PaymentStatus.ABERTO;
        var qrCode = "QRCODE";
        var externalId = "external-id";
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Payment actual = new Payment(
            id,
            order,
            status,
            qrCode,
            externalId,
            createdAt,
            modifiedAt,
            null
        );

        assertEquals(id, actual.getId());
        assertEquals(order, actual.getOrder());
        assertEquals(status, actual.getStatus());
        assertEquals(qrCode, actual.getQrCode());
        assertEquals(externalId, actual.getExternalId());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

}
