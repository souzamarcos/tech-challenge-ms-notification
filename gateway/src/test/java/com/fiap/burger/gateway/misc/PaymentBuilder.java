package com.fiap.burger.gateway.misc;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentBuilder {

    private Long id = 1L;

    private Order order = new OrderBuilder().build();

    private PaymentStatus paymentStatus = PaymentStatus.ABERTO;

    private String qrCode = "QR-CODE";

    private String externalId = "external-id";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt = null;

    public PaymentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PaymentBuilder withStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public Payment build() {
        return new Payment(id, order, paymentStatus, qrCode, externalId, createdAt, modifiedAt, deletedAt);
    };

}
