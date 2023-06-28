package com.fiap.burger.domain.adapter.repository.order;

import com.fiap.burger.domain.entities.order.Order;

public interface OrderRepository {
    Order save(Order order);
}
