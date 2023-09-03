package com.fiap.burger.gateway.payment.model;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import com.fiap.burger.gateway.order.model.OrderJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "payment")
@Entity
public class PaymentJPA extends BaseDomainJPA {

    @JoinColumn(name = "order_id", insertable = true, updatable = false)
    @ManyToOne(optional = false)
    OrderJPA orderJPA;
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
        OrderJPA orderJPA,
        PaymentStatus status,
        String qrCode,
        String externalId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt
    ) {
        this.id = id;
        this.orderJPA = orderJPA;
        this.status = status;
        this.qrCode = qrCode;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public OrderJPA getOrderJPA() {
        return orderJPA;
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
                getOrderJPA(),
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
                OrderJPA.toJPA(payment.getOrder()),
                payment.getStatus(),
                payment.getQrCode(),
                payment.getExternalId(),
                payment.getCreatedAt(),
                payment.getModifiedAt(),
                payment.getDeletedAt()
        );
    }

    public Payment toEntity() {
        return new Payment(id, orderJPA.toEntity(), status, qrCode, externalId, createdAt, modifiedAt, deletedAt);
    }
}