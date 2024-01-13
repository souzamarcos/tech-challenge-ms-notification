package com.fiap.burger.application.utils;

import com.fiap.burger.api.dto.payment.request.PaymentWebhookRequestDto;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.listener.order.OrderMessageListenerDto;

public class PaymentHelper {

    public static PaymentWebhookRequestDto createWebhookRequest(Long id, PaymentStatus paymentStatus) {
        return new PaymentWebhookRequestDto(id, paymentStatus);
    }
    public static OrderMessageListenerDto createOrderMessageListenerDto(Long id) {
        return new OrderMessageListenerDto(id);
    }
}
