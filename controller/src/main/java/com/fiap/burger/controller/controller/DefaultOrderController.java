package com.fiap.burger.controller.controller;

import com.fiap.burger.controller.adapter.api.OrderController;
import com.fiap.burger.entity.entity.order.Order;
import com.fiap.burger.entity.entity.order.OrderStatus;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import com.fiap.burger.usecase.adapter.gateway.OrderGateway;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.misc.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultOrderController implements OrderController {
    @Autowired
    private OrderUseCase useCase;
    @Autowired
    OrderGateway orderGateway;
    @Autowired
    ClientGateway clientGateway;
    @Autowired
    ProductGateway productGateway;

    @Override
    public Order insert(Order order) {
        return useCase.insert(orderGateway, productGateway, clientGateway, order);
    }

    @Override
    public Order findById(Long orderId) {
        var persistedOrder = useCase.findById(orderGateway, orderId);
        if (persistedOrder == null) throw new OrderNotFoundException(orderId);
        return persistedOrder;
    }

    @Override
    public Order updateStatus(Long orderId, OrderStatus newStatus) {
        return useCase.updateStatus(orderGateway, orderId, newStatus);
    }

    @Override
    public List<Order> findAllBy(OrderStatus status) {
        return useCase.findAllBy(orderGateway, status);
    }

    @Override
    public List<Order> findAllInProgress() {
        return useCase.findAllInProgress(orderGateway);
    }

    @Override
    public Order checkout(Long orderId) {
        return useCase.checkout(orderGateway, orderId);
    }
}

