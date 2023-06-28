package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.entities.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public Order insert(Order order) {

//        validateProductToInsert(order);
        return repository.save(order);
    }

}
