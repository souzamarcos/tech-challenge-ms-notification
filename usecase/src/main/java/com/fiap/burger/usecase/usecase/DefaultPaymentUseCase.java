package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.OrderAlreadyPaidException;
import com.fiap.burger.usecase.misc.exception.OrderNotFoundException;
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

        if(order == null) {
            //TODO: Evaluate if is better to use this Exception on orderUseCase.findById(orderId)
            throw new OrderNotFoundException(orderId);
        }

        List<Payment> persistedPayments = findByOrderId(order.getId());

        //TODO: Change this to verify orderStatus
        if(isThereAnyApprovedPayment(persistedPayments)) {
            throw new OrderAlreadyPaidException(orderId);
        }

        return paymentGateway.save(Payment.createPaymentWithOrderAndOpenStatus(order));
    }

    private boolean isThereAnyApprovedPayment(List<Payment> payments) {
        List<Payment> approvedPayments = payments.stream().filter(element -> element.getStatus() == PaymentStatus.APROVADO).toList();
        return approvedPayments.size() >= 1;
    }


}
