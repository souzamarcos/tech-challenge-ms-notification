package com.fiap.burger.gateway.payment.gateway;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.gateway.payment.dao.PaymentDAO;
import com.fiap.burger.gateway.payment.model.PaymentJPA;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DefaultPaymentGateway implements PaymentGateway {
    @Autowired
    PaymentDAO paymentDAO;

    @Override
    public Payment findById(Long id) {
        return paymentDAO.findById(id).map(PaymentJPA::toEntity).orElse(null);
    }
}

