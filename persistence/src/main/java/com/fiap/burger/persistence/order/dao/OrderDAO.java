package com.fiap.burger.persistence.order.dao;

import com.fiap.burger.persistence.order.model.OrderJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderJPA, Long> {

}
