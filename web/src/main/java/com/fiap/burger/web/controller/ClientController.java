package com.fiap.burger.web.controller;

import com.fiap.burger.domain.misc.exception.ClientNotFoundException;
import com.fiap.burger.domain.service.ClientService;
import com.fiap.burger.web.dto.client.response.ClientResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Tag(name = "cliente", description = "API responsável pelo controle de clientes.")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Operation(summary = "Consultar cliente", description = "Consultar um cliente", tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{clientId}")
    public ClientResponseDto findById(@PathVariable Long clientId) {
        var persistedClient = clientService.findById(clientId);
        if (persistedClient == null) throw new ClientNotFoundException();
        return ClientResponseDto.toResponseDto(persistedClient);
    }
}
