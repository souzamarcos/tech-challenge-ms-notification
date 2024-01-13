package com.fiap.burger.listener.order;

import com.fiap.burger.controller.adapter.api.PaymentController;
import com.fiap.burger.usecase.misc.exception.OrderMessageListenerException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class InMemoryOrderMessageListener {
    @Autowired
    PaymentController paymentController;

    public void orderQueueListener(String message) {
        try {
            var dto = new Gson().fromJson(message, OrderMessageListenerDto.class);
            paymentController.insert(dto.getId());
        } catch (Exception ex) {
            throw new OrderMessageListenerException("An exception was thrown during the execution of the SQS listener method and Message will be still available in Queue");
        }
    }
}
