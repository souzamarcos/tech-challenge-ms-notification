package com.fiap.burger.listener;

import com.fiap.burger.controller.adapter.api.NotificationController;
import com.fiap.burger.usecase.misc.exception.NotificationMessageListenerException;
import com.fiap.burger.usecase.misc.profiles.Production;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Production
@Component
public class DefaultMessageListener {
    @Autowired
    NotificationController controller;

    @SqsListener("${cloud.aws.sqs.payment-queue}")
    public void notificationsQueueListener(String message) {
        try {
            var dto = new Gson().fromJson(message, MessageListenerDto.class);
            controller.sendNotification(dto.getCustomerId());
        } catch (Exception ex) {
            throw new NotificationMessageListenerException(ex.getMessage());
        }
    }
}
