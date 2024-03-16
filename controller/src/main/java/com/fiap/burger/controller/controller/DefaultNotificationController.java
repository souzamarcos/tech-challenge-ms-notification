package com.fiap.burger.controller.controller;

import com.fiap.burger.controller.adapter.api.NotificationController;
import com.fiap.burger.entity.common.NotificationType;
import com.fiap.burger.usecase.adapter.usecase.NotificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultNotificationController implements NotificationController {
    @Autowired
    private NotificationUseCase useCase;

    @Override
    public String sendNotification(String customerId, Long orderId,  NotificationType notificationType) {
        return useCase.sendNotification(customerId, orderId, notificationType);
    }
}

