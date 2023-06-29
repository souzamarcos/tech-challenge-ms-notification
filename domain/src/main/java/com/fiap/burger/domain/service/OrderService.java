package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.misc.exception.ClientNotFoundException;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order insert(Order order) {
        validateOrderToInsert(order);
        return orderRepository.save(order);
    }

    private void validateOrderToInsert(Order order) {
        if(order.getClientId() != null) {
            var client = clientRepository.findById(order.getClientId());
            if (client == null) {
                throw new InvalidAttributeException("Client '" + order.getClientId() + "' not found.", "clientId");
            }
        }

        if(order.getStatus() != OrderStatus.RECEBIDO) {
            throw new InvalidAttributeException("Order should be created with status 'RECEBIDO'", "id");
        }
        validateOrder(order);
    }

    private void validateOrder(Order order) {
        //TODO implementar
    }

}
