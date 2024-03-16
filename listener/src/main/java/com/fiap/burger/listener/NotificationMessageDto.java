package com.fiap.burger.listener;

import com.fiap.burger.entity.common.NotificationType;
import jakarta.validation.constraints.NotNull;

public record NotificationMessageDto(
    @NotNull
    String customerId,
    @NotNull
    Long orderId,
    @NotNull
    NotificationType notificationType
) {}
