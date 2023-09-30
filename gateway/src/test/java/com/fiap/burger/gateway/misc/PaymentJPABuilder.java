package com.fiap.burger.gateway.misc;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.order.model.OrderJPA;
import com.fiap.burger.gateway.payment.model.PaymentJPA;

import java.time.LocalDateTime;

public class PaymentJPABuilder {

    private Long id = 1L;

    private OrderJPA order = new OrderJPABuilder().build();

    private PaymentStatus paymentStatus = PaymentStatus.ABERTO;

    private String qrCode = "QR-CODE";

    private String externalId = "external-id";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt = null;

    public PaymentJPABuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PaymentJPABuilder withStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public PaymentJPA build() {
        return new PaymentJPA(id, order, paymentStatus, qrCode, externalId, createdAt, modifiedAt, deletedAt);
    };

}
