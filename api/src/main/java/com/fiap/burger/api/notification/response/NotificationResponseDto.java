package com.fiap.burger.api.notification.response;

import jakarta.validation.constraints.NotNull;

public record NotificationResponseDto(
    @NotNull
    String message
) {

    public static NotificationResponseDto toResponseDto(String message) {
        return new NotificationResponseDto(message);
    }
}
