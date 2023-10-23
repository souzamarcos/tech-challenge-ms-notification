package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.OrderGateway;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;
import com.fiap.burger.usecase.misc.exception.OrderCannotBePaidException;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;

import java.time.LocalDateTime;
import java.util.List;


public class DefaultPaymentUseCase implements PaymentUseCase {

    private final PaymentGateway paymentGateway;

    private final OrderGateway orderGateway;

    public DefaultPaymentUseCase(PaymentGateway paymentGateway, OrderGateway orderGateway) {
        this.paymentGateway = paymentGateway;
        this.orderGateway = orderGateway;
    }

    public Payment findById(Long id) {
        return paymentGateway.findById(id);
    }

    public List<Payment> findByOrderId(Long orderId) {
        return paymentGateway.findByOrderId(orderId);
    }

    public Payment insert(Long orderId) {

        Order order = orderGateway.findById(orderId);

        if (!order.canBePaid()) {
            throw new OrderCannotBePaidException(orderId);
        }

        return paymentGateway.save(Payment.createPaymentWithOrderAndOpenStatus(order));
    }

    @Override
    public Payment updateStatus(Long id, PaymentStatus status) {
        Payment persistedPayment = findById(id);

        if (persistedPayment == null) {
            throw new PaymentNotFoundException(id);
        }

        validateUpdateStatus(status, persistedPayment.getStatus());

        paymentGateway.updatePaymentStatus(id, status, LocalDateTime.now());
        persistedPayment.setStatus(status);
        return persistedPayment;
    }

    private void validateUpdateStatus(PaymentStatus newStatus, PaymentStatus oldStatus) {
        if (!canStatusBeUpdatedFrom(oldStatus)) {
            throw new InvalidAttributeException(String.format("You can not change status from payments with status %s.", oldStatus.name()), "oldStatus");
        }
        if (!canStatusUpdateTo(newStatus)) {
            throw new InvalidAttributeException(String.format("You can not change payments status to %s.", newStatus.name()), "new Status");
        }
    }

    private boolean canStatusBeUpdatedFrom(PaymentStatus status) {
        List<PaymentStatus> statusThatCanBeUpdatedFrom = List.of(PaymentStatus.ABERTO);
        return statusThatCanBeUpdatedFrom.contains(status);
    }

    private boolean canStatusUpdateTo(PaymentStatus status) {
        List<PaymentStatus> statusThatCanBeUpdatedTo = List.of(PaymentStatus.APROVADO, PaymentStatus.RECUSADO);
        return statusThatCanBeUpdatedTo.contains(status);
    }
}
