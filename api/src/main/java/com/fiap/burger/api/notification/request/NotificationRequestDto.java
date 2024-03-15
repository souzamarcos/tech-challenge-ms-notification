package com.fiap.burger.api.notification.request;

import com.fiap.burger.entity.common.NotificationType;
import jakarta.validation.constraints.NotNull;

public record NotificationRequestDto(
    @NotNull
    String customerId,
    @NotNull
    Long orderId,
    @NotNull
    NotificationType notificationType
) {}
