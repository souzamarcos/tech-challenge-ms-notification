package com.fiap.burger.persistence.order.repository;

import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.persistence.order.dao.OrderDAO;
import com.fiap.burger.persistence.order.model.OrderJPA;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderRepository implements OrderRepository {
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
    public Order save(Order product) {
        return orderDAO.save(OrderJPA.toJPA(product)).toEntityWithItems();
    }
}
