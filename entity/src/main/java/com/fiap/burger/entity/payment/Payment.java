package com.fiap.burger.entity.payment;

import com.fiap.burger.entity.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment extends BaseEntity {

    private Long orderId;
    private PaymentStatus status;
    private String qrCode;
    private String externalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return getOrderId().equals(payment.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Payment(
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

    public Payment(
            Long id,
            Long orderId,
            PaymentStatus status,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime deletedAt
    ) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Payment(
            Long id,
            Long orderId,
            PaymentStatus status
    ) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }
}
