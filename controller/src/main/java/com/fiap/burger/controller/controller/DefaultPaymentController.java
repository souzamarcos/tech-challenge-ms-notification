package com.fiap.burger.controller.controller;

import com.fiap.burger.controller.adapter.api.PaymentController;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultPaymentController implements PaymentController {

    @Autowired
    private PaymentUseCase useCase;
    @Override
    public Payment findById(Long id) {
        var persistedPayment = useCase.findById(id);
        if (persistedPayment == null) throw new PaymentNotFoundException(id);
        return persistedPayment;
    }

    @Override
    public List<Payment> findByOrderId(Long orderId) {
        var persistedPayment = useCase.findByOrderId(orderId);
        return persistedPayment;
    }

    @Override
    public Payment insert(Long orderId) {
        return useCase.insert(orderId);
    }
}
