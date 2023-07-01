package com.fiap.burger.persistence.order.dao;

import com.fiap.burger.persistence.order.model.OrderItemJPA;
import com.fiap.burger.persistence.order.model.OrderJPA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItemJPA, Long> {
    List<OrderItemJPA> findAllByOrderId(Long orderId);
}
