package com.fiap.burger.domain.adapter.repository.order;

import com.fiap.burger.domain.entities.order.OrderItemAdditional;
import java.util.List;
public interface OrderItemAdditionalRepository {
    List<OrderItemAdditional> saveAll(List<OrderItemAdditional> orderItemAdditionals);

}
