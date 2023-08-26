package com.fiap.burger.web.controller;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.adapter.service.OrderService;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.misc.exception.OrderNotFoundException;
import com.fiap.burger.web.adapter.api.order.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultOrderController implements OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Order insert(Order order) {
        return service.insert(orderRepository, productRepository, clientRepository, order);
    }

    @Override
    public Order findById(Long orderId) {
        var persistedOrder = service.findById(orderRepository, orderId);
        if (persistedOrder == null) throw new OrderNotFoundException(orderId);
        return persistedOrder;
    }

    @Override
    public Order updateStatus(Long orderId, OrderStatus newStatus) {
        return service.updateStatus(orderRepository, orderId, newStatus);
    }

    @Override
    public List<Order> findAllBy(OrderStatus status) {
        return service.findAllBy(orderRepository, status);
    }

    @Override
    public List<Order> findAllInProgress() {
        return service.findAllInProgress(orderRepository);
    }

    @Override
    public Order checkout(Long orderId) {
        return service.checkout(orderRepository, orderId);
    }
}
