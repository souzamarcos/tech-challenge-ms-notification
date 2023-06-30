package com.fiap.burger.domain.adapter.repository.order;

import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import java.util.List;

public interface OrderItemRepository {

    OrderItem findById(Long id);
    List<OrderItem> findAllBy(Long orderId);
    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
