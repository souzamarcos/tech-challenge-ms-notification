package com.fiap.burger.listener;

import com.fiap.burger.controller.adapter.api.NotificationController;
import com.fiap.burger.usecase.misc.exception.NotificationMessageListenerException;
import com.fiap.burger.usecase.misc.profiles.Test;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Test
@Component
public class InMemoryMessageListener {
    @Autowired
    NotificationController controller;

    public void notificationQueueListener(String message) {
        try {
            var dto = new Gson().fromJson(message, MessageListenerDto.class);
            controller.sendNotification(dto.getCustomerId());
        } catch (Exception ex) {
            throw new NotificationMessageListenerException(ex.getMessage());
        }
    }
}
