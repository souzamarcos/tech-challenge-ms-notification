package com.fiap.burger.usecase.adapter.usecase;


import com.fiap.burger.entity.common.NotificationType;

public interface NotificationUseCase {

    String sendNotification(String customerId, Long orderId,  NotificationType notificationType);

}
