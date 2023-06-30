package com.fiap.burger.web.controller;

import com.fiap.burger.domain.entities.client.Client;
import com.fiap.burger.domain.misc.exception.ClientNotFoundException;
import com.fiap.burger.domain.service.ClientService;
import com.fiap.burger.web.dto.client.request.ClientInsertRequestDto;
import com.fiap.burger.web.dto.client.response.ClientResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "cliente", description = "API responsável pelo controle de clientes.")
public class ClientController {

    @Autowired
    private ClientService clientService;

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

    @Operation(summary = "Cadastrar cliente", description = "Cadastrar um cliente", tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Cliente inválido")
    })
    @PostMapping
    public ClientResponseDto insert(@RequestBody ClientInsertRequestDto clientDto) {
        System.out.println(clientDto);
        System.out.println(clientDto.toEntity());
        Client persistedClient = clientService.insert(clientDto.toEntity());
        return ClientResponseDto.toResponseDto(persistedClient);
    }
}
