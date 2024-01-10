package com.fiap.burger.gateway.misc;

import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.payment.model.PaymentJPA;

import java.time.LocalDateTime;

public class PaymentJPABuilder {

    private Long id = 1L;

    private Long orderId = 1L;

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
        return new PaymentJPA(id, orderId, paymentStatus, qrCode, externalId, createdAt, modifiedAt, deletedAt);
    }

    ;

}
