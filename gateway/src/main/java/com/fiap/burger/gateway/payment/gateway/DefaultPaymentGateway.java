package com.fiap.burger.gateway.payment.gateway;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.gateway.order.dao.OrderDAO;
import com.fiap.burger.gateway.order.model.OrderJPA;
import com.fiap.burger.gateway.payment.dao.PaymentDAO;
import com.fiap.burger.gateway.payment.model.PaymentJPA;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;


@Repository
public class DefaultPaymentGateway implements PaymentGateway {
    @Autowired
    PaymentDAO paymentDAO;

    @Autowired
    OrderDAO orderDAO;

    @Override
    public Payment findById(Long id) {
        return paymentDAO.findById(id).map(PaymentJPA::toEntity).orElse(null);
    }


    @Override
    public List<Payment> findByOrderId(Long orderId) {
        OrderJPA orderJPA = orderDAO.findById(orderId).orElse(null);

        if (orderJPA != null) {
            return paymentDAO.findAllByDeletedAtNullAndOrderJPA(orderJPA).stream().map(PaymentJPA::toEntity).toList();
        } else {
            return Collections.emptyList();
        }
    }

}

