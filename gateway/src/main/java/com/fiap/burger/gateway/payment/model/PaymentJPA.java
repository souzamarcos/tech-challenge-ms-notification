package com.fiap.burger.gateway.payment.model;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "payment")
@Entity
public class PaymentJPA extends BaseDomainJPA {

    @Enumerated(EnumType.STRING)
    @Column(name = "order_id")
    Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PaymentStatus status;

    @Column
    String qrCode;

    @Column
    String externalId;

    public PaymentJPA() {

    }

    public PaymentJPA(
        Long id,
        Long orderId,
        PaymentStatus status,
        String qrCode,
        String externalId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt
    ) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.qrCode = qrCode;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getExternalId() {
        return externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentJPA paymentJPA)) return false;
        return Objects.equals(hashCode(), paymentJPA.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getId(),
            getStatus(),
            getQrCode(),
            getExternalId(),
            getCreatedAt(),
            getModifiedAt(),
            getDeletedAt()
        );
    }

    public static PaymentJPA toJPA(Payment payment) {
        return new PaymentJPA(
            payment.getId(),
            payment.getOrderId(),
            payment.getStatus(),
            payment.getQrCode(),
            payment.getExternalId(),
            payment.getCreatedAt(),
            payment.getModifiedAt(),
            payment.getDeletedAt()
        );
    }

    public Payment toEntity() {
        return new Payment(id, orderId, status, qrCode, externalId, createdAt, modifiedAt, deletedAt);
    }
}