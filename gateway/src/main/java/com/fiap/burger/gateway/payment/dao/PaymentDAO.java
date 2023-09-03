package com.fiap.burger.gateway.payment.dao;


import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.order.model.OrderJPA;
import com.fiap.burger.gateway.payment.model.PaymentJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentDAO extends JpaRepository<PaymentJPA, Long> {

    List<PaymentJPA> findAllByDeletedAtNullAndOrderJPA(OrderJPA orderJPA);



}
