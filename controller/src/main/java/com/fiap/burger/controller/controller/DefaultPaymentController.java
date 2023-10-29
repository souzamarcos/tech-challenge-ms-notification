package com.fiap.burger.controller.controller;

import com.fiap.burger.controller.adapter.api.PaymentController;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.burger.entity.payment.PaymentStatus.APROVADO;
import static com.fiap.burger.entity.payment.PaymentStatus.RECUSADO;

@Component
public class DefaultPaymentController implements PaymentController {

    @Autowired
    private PaymentUseCase useCase;
    @Autowired
    private PaymentGateway gateway;
    @Autowired
    private OrderUseCase orderUseCase;

    @Override
    public Payment findById(Long id) {
        var persistedPayment = useCase.findById(id);
        if (persistedPayment == null) throw new PaymentNotFoundException(id);
        return persistedPayment;
    }

    @Override
    public List<Payment> findByOrderId(Long orderId) {
        var persistedPayment = useCase.findByOrderId(orderId);
        if (persistedPayment == null) throw new PaymentNotFoundException();
        return persistedPayment;
    }

    @Override
    public Payment insert(Long orderId) {
        return useCase.insert(orderId);
    }

    @Override
    public void updateStatus(Long id, PaymentStatus status) {
        var persistedPayment = useCase.updateStatus(id, status);
        if (APROVADO == status) {
            orderUseCase.checkout(persistedPayment.getOrder().getId());
        } else if (RECUSADO == status) {
            orderUseCase.updateStatus(persistedPayment.getOrder().getId(), OrderStatus.CANCELADO);
        }
    }
}
