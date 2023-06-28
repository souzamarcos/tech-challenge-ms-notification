package com.fiap.burger.domain.adapter.repository.order;

import com.fiap.burger.domain.entities.order.Order;
import java.util.List;

public interface OrderRepository {
//    Order save(Order order);

    List<Order> findAll();
}
