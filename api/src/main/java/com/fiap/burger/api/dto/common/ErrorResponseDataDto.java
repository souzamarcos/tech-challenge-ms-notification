package com.fiap.burger.api.dto.common;

import com.fiap.burger.domain.misc.exception.DomainException;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record ErrorResponseDataDto(
        @NotNull String message,
        @NotNull Map<String, String> parameters
) {
    public static ErrorResponseDataDto toErrorResponseDataDto(DomainException exception) {
        return new ErrorResponseDataDto(exception.getMessage(), exception.getParameters());
    }
}
