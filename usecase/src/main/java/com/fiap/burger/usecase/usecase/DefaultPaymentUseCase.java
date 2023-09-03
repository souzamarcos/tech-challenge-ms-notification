package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.OrderAlreadyPaidException;
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

        List<Payment> persistedPayments = findByOrderId(order.getId());

        if(isThereAnyApprovedPayment(persistedPayments)) {
            throw new OrderAlreadyPaidException(orderId);
        }

        return paymentGateway.save(Payment.createPaymentWithOrderAndOpenStatus(order));
    }

    @Override
    public void updateStatus(Long id, PaymentStatus status) {
        paymentGateway.updatePaymentStatus(id, status);

        Payment persistedPayment = findById(id);

        if(persistedPayment.getStatus() == PaymentStatus.APROVADO) {
            orderUseCase.checkout(persistedPayment.getOrder().getId());
        }
    }

    private boolean isThereAnyApprovedPayment(List<Payment> payments) {
        List<Payment> approvedPayments = payments.stream().filter(element -> element.getStatus() == PaymentStatus.APROVADO).toList();
        return approvedPayments.size() >= 1;
    }


}
