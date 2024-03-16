package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.common.NotificationType;

public interface NotificationController {
    String sendNotification(String customerId, Long orderId, NotificationType notificationType);
}
