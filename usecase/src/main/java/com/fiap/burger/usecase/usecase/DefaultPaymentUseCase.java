package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;
import com.fiap.burger.usecase.misc.exception.OrderCannotBePaidException;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DefaultPaymentUseCase implements PaymentUseCase {

    private final PaymentGateway paymentGateway;

    @Autowired
    OrderUseCase orderUseCase;

    public DefaultPaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment findById(Long id) {
        return paymentGateway.findById(id);
    }

    public List<Payment> findByOrderId(Long orderId) {
        return paymentGateway.findByOrderId(orderId);
    }

    public Payment insert(Long orderId) {

        Order order = orderUseCase.findById(orderId);

        if (!orderUseCase.canBePaid(order.getStatus())) {
            throw new OrderCannotBePaidException(orderId);
        }

        return paymentGateway.save(Payment.createPaymentWithOrderAndOpenStatus(order));
    }

    @Override
    public void updateStatus(Long id, PaymentStatus status) {
        Payment persistedPayment = findById(id);

        if (persistedPayment == null) {
            throw new InvalidAttributeException("Payment not found", "id");
        }

        paymentGateway.updatePaymentStatus(id, status);

        switch (status) {
            case APROVADO -> orderUseCase.checkout(persistedPayment.getOrder().getId());
            case RECUSADO -> orderUseCase.updateStatus(persistedPayment.getOrder().getId(), OrderStatus.CANCELADO);
        }
    }
}
