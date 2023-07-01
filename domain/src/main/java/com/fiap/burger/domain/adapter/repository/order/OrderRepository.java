package com.fiap.burger.domain.adapter.repository.order;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.product.Product;
import java.util.List;

public interface OrderRepository {

    Order findById(Long id);
    List<Order> findAll();
    Order save(Order order);

    Order save2(Order order);
}
