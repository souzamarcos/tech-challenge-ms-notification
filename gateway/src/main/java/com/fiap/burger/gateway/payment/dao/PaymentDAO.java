package com.fiap.burger.gateway.payment.dao;


import com.fiap.burger.gateway.payment.model.PaymentJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDAO extends JpaRepository<PaymentJPA, Long> {

}
