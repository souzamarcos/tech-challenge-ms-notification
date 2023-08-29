package com.fiap.burger.gateway.order.gateway;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.gateway.order.dao.OrderDAO;
import com.fiap.burger.gateway.order.model.OrderJPA;
import com.fiap.burger.usecase.adapter.gateway.OrderGateway;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DefaultOrderGateway implements OrderGateway {
    @Autowired
    OrderDAO orderDAO;

    @Override
    public Order findById(Long id) {
        return orderDAO.findById(id).map(OrderJPA::toEntityWithItems).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAllByDeletedAtNull().stream().map(OrderJPA::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllBy(OrderStatus status) {
        return orderDAO.findAllByDeletedAtNullAndStatusEquals(status)
            .stream()
            .map(OrderJPA::toEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllInProgress() {
        var inProgressStatuses = Set.of(OrderStatus.RECEBIDO, OrderStatus.EM_PREPARACAO, OrderStatus.PRONTO);
        return orderDAO.findAllInProgress(inProgressStatuses)
            .stream()
            .map(OrderJPA::toEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Order save(Order product) {
        return orderDAO.save(OrderJPA.toJPA(product)).toEntityWithItems();
    }

    @Override
    @Transactional
    public void updateStatus(Long id, OrderStatus newStatus, LocalDateTime modifiedAt) {
        orderDAO.updateStatus(id, newStatus, modifiedAt);
    }
}