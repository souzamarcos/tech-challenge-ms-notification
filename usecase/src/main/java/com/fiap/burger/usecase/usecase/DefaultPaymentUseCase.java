package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.OrderCannotBePaidException;
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

        if(!orderUseCase.canBePaid(order.getStatus())) {
            throw new OrderCannotBePaidException(orderId);
        }

        return paymentGateway.save(Payment.createPaymentWithOrderAndOpenStatus(order));
    }

    @Override
    public void updateStatus(Long id, PaymentStatus status) {
        paymentGateway.updatePaymentStatus(id, status);

        Payment persistedPayment = findById(id);

        if(PaymentStatus.APROVADO.equals(persistedPayment.getStatus())) {
            orderUseCase.checkout(persistedPayment.getOrder().getId());
        }

        if(PaymentStatus.RECUSADO.equals(persistedPayment.getStatus())) {
            orderUseCase.updateStatus(persistedPayment.getOrder().getId(), OrderStatus.CANCELADO);
        }
    }

    private boolean isThereAnyApprovedPayment(List<Payment> payments) {
        List<Payment> approvedPayments = payments.stream().filter(element ->  PaymentStatus.APROVADO.equals(element.getStatus())).toList();
        return approvedPayments.size() >= 1;
    }


}
