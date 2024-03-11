package com.fiap.burger.listener;


import com.fiap.burger.entity.common.OrderPaymentStatus;

public class MessageListenerDto {

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderPaymentStatus getStatus() {
        return status;
    }

    public void setStatus(OrderPaymentStatus status) {
        this.status = status;
    }

    private OrderPaymentStatus status;

    public MessageListenerDto(String customerId, OrderPaymentStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public OrderPaymentStatus getOrderPaymentStatus() {
        return status;
    }
}
