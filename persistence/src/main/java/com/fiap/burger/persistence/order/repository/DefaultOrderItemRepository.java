package com.fiap.burger.persistence.order.repository;

import com.fiap.burger.domain.adapter.repository.order.OrderItemRepository;
import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.persistence.order.dao.OrderDAO;
import com.fiap.burger.persistence.order.dao.OrderItemDAO;
import com.fiap.burger.persistence.order.model.OrderItemJPA;
import com.fiap.burger.persistence.order.model.OrderJPA;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderItemRepository implements OrderItemRepository {
    @Autowired
    OrderItemDAO orderItemDAO;

    @Override
    public OrderItem findById(Long id) {
        return orderItemDAO.findById(id).map(OrderItemJPA::toEntity).orElse(null);
    }

    @Override
    public List<OrderItem> findAllBy(Long orderId) {
        return orderItemDAO.findAllByOrderId(orderId).stream().map(OrderItemJPA::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemDAO.saveAll(orderItems.stream().map(OrderItemJPA::toJPA).collect(Collectors.toList()))
            .stream()
            .map(OrderItemJPA::toEntity)
            .collect(Collectors.toList());
    }
}