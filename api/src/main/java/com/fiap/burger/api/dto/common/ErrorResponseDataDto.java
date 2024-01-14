package com.fiap.burger.api.dto.common;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record ErrorResponseDataDto(
    @NotNull String message,
    @NotNull Map<String, String> parameters
) {
}
