package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderGateway {
    Order findById(Long id);

    List<Order> findAll();

    List<Order> findAllBy(OrderStatus status);

    List<Order> findAllInProgress();

    Order save(Order order);

    void updateStatus(Long id, OrderStatus newStatus, LocalDateTime modifiedAt);

    boolean canBePaid(OrderStatus status);
}
