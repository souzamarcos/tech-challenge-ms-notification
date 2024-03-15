package com.fiap.burger.listener;

import com.fiap.burger.controller.adapter.api.NotificationController;
import com.fiap.burger.usecase.misc.exception.NotificationMessageListenerException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageListener {
    @Autowired
    NotificationController controller;

    @SqsListener("${cloud.aws.sqs.notification-queue}")
    public void notificationsQueueListener(String message) {
        try {
            var dto = new Gson().fromJson(message, NotificationMessageDto.class);
            controller.sendNotification(dto.customerId(), dto.orderId(), dto.notificationType());
        } catch (Exception ex) {
            throw new NotificationMessageListenerException(ex.getMessage());
        }
    }
}
