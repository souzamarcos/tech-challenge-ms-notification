package com.fiap.burger.entity.payment;

import com.fiap.burger.entity.common.BaseEntity;
import com.fiap.burger.entity.order.Order;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment extends BaseEntity {

    private Order order;
    private PaymentStatus status;
    private String qrCode;
    private String externalId;

    public static Payment createPaymentWithOrderAndOpenStatus(Order order) {
        return new Payment(order, PaymentStatus.ABERTO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return getOrder().equals(payment.getOrder()) && getStatus() == payment.getStatus() && Objects.equals(getQrCode(), payment.getQrCode()) && Objects.equals(getExternalId(), payment.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getStatus(), getQrCode(), getExternalId());
    }

    public Order getOrder() {
        return order;
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
            Order order,
            PaymentStatus status,
            String qrCode,
            String externalId,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime deletedAt
    ) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.qrCode = qrCode;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Payment(
            Long id,
            Order order,
            PaymentStatus status,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime deletedAt
    ) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Payment(
            Long id,
            Order order,
            PaymentStatus status
    ) {
        this.id = id;
        this.order = order;
        this.status = status;
    }

    public Payment(
            Order order,
            PaymentStatus status
    ) {
        this.order = order;
        this.status = status;
    }
}
