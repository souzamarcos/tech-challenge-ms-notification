package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.entity.order.Order;
import com.fiap.burger.entity.entity.order.OrderStatus;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import com.fiap.burger.usecase.adapter.gateway.OrderGateway;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;

import java.util.List;

public interface OrderUseCase {
    Order findById(OrderGateway orderGateway,  Long id);
    List<Order> findAll(OrderGateway orderGateway);
    List<Order> findAllBy(OrderGateway orderGateway, OrderStatus status);
    List<Order> findAllInProgress(OrderGateway orderGateway);
    Order insert(OrderGateway orderGateway, ProductGateway productGateway, ClientGateway clientGateway, Order order);
    Order updateStatus(OrderGateway orderGateway, Long orderId, OrderStatus newStatus);
    Order checkout(OrderGateway orderGateway, Long orderId);
}
