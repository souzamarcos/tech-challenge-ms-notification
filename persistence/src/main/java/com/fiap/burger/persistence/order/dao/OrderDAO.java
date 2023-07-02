package com.fiap.burger.persistence.order.dao;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.persistence.order.model.OrderJPA;
import com.fiap.burger.persistence.product.model.ProductJPA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDAO extends JpaRepository<OrderJPA, Long> {

    List<OrderJPA> findAllByDeletedAtNull();

    @Modifying
    @Query("update OrderJPA o set o.status = :newStatus where o.id = :id")
    void updateStatus(@Param("id") Long id, @Param("newStatus") OrderStatus newStatus);

}
