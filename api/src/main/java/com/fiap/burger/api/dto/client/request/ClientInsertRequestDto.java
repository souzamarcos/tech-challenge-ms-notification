package com.fiap.burger.api.dto.client.request;

import com.fiap.burger.entity.entity.client.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientInsertRequestDto (

        @NotBlank String cpf,
        @NotBlank
        @Email
        String email,

        @NotBlank String name
) {

    public Client toEntity() {
        return new Client(cpf, email, name);
    }

    @Override
    public String toString() {
        return "ClientInsertRequestDto{" +
                "cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
