package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public Order insert(Order order) {
        validateOrderToInsert(order);
        return repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    private void validateOrderToInsert(Order order) {
        if(order.getStatus() != OrderStatus.AGUARDANDO_PAGAMENTO) {
            throw new InvalidAttributeException("Order should be created with status 'AGUARDANDO_PAGAMENTO'", "id");
        }
        validateOrder(order);
    }

    private void validateOrder(Order order) {
        //TODO implementar
    }

}
