package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import com.fiap.burger.domain.misc.exception.NegativeOrZeroValueException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order insert(Order order) {
        order.setTotal(calculateTotal(order.getItems()));
        validateOrderToInsert(order);
        var persistedOrder = orderRepository.save(order);
        return persistedOrder;
    }

    // TODO refatorar validação e calculo de total pra ficar numa estrutura mais legível

    private Double calculateTotal(List<OrderItem> items) {
        AtomicReference<Double> itemsTotal = new AtomicReference<>(0.0);
        items.forEach(item -> {
            var product = productRepository.findById(item.getProductId());
            if (product == null) {
                throw new InvalidAttributeException("Product '" + item.getProductId() + "' not found.", "items.productId");
            }
            itemsTotal.updateAndGet(v -> v + product.getValue());
        });
        return itemsTotal.get();
    }

    private void validateOrderToInsert(Order order) {
        if (order.getClientId() != null) {
            var client = clientRepository.findById(order.getClientId());
            if (client == null) {
                throw new InvalidAttributeException("Client '" + order.getClientId() + "' not found.", "clientId");
            }
        }

        if (order.getStatus() != OrderStatus.RECEBIDO) {
            throw new InvalidAttributeException("Order should be created with status 'RECEBIDO'", "id");
        }
        validateOrder(order);
    }

    private void validateOrder(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty())
            throw new InvalidAttributeException("Order items should not be empty", "items");

        if (order.getTotal() <= 0) throw new NegativeOrZeroValueException("total");
    }
}
