package com.fiap.burger.web.dto.client.response;

import com.fiap.burger.domain.entities.client.Client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientResponseDto (

    @NotBlank
    Long id,
    @NotBlank
    String cpf,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String name
) {
    public static ClientResponseDto toResponseDto(Client client) {
        return new ClientResponseDto(
                client.getId(),
                client.getCpf(),
                client.getEmail(),
                client.getName()
        );
    }
}
