package com.fiap.burger.domain.adapter.service;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;

import java.util.List;

public interface OrderService {
    Order findById(OrderRepository orderRepository,  Long id);
    List<Order> findAll(OrderRepository orderRepository);
    List<Order> findAllBy(OrderRepository orderRepository, OrderStatus status);
    List<Order> findAllInProgress(OrderRepository orderRepository);
    Order insert(OrderRepository orderRepository, ProductRepository productRepository, ClientRepository clientRepository, Order order);
    Order updateStatus(OrderRepository orderRepository, Long orderId, OrderStatus newStatus);
    Order checkout(OrderRepository orderRepository, Long orderId);
}
