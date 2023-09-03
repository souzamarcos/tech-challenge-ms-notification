package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import com.fiap.burger.usecase.adapter.gateway.OrderGateway;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;

import java.util.List;

public interface OrderUseCase {
    Order findById(Long id);

    List<Order> findAll();

    List<Order> findAllBy(OrderStatus status);

    List<Order> findAllInProgress();

    Order insert(Order order);

    Order updateStatus(Long orderId, OrderStatus newStatus);

    Order checkout(Long orderId);

    boolean canBePaid(OrderStatus status);
}
