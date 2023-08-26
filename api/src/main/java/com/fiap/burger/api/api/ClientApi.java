package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.client.request.ClientInsertRequestDto;
import com.fiap.burger.api.dto.client.response.ClientResponseDto;
import com.fiap.burger.controller.adapter.api.ClientController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "cliente", description = "API responsável pelo controle de clientes.")
public class ClientApi {

    @Autowired
    ClientController clientController;

    @Operation(summary = "Consultar cliente", description = "Consultar um cliente", tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{clientId}")
    public ClientResponseDto findById(@PathVariable Long clientId) {
        return ClientResponseDto.toResponseDto(clientController.findById(clientId));
    }

    @Operation(summary = "Buscar cliente por cpf", description = "Buscar cliente por cpf", tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/by-cpf/{clientCpf}")
    public ClientResponseDto findByCpf(@PathVariable String clientCpf) {
        return ClientResponseDto.toResponseDto(clientController.findByCpf(clientCpf));
    }

    @Operation(summary = "Cadastrar cliente", description = "Cadastrar um cliente", tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Cliente inválido")
    })
    @PostMapping()
    public ClientResponseDto insert(@RequestBody ClientInsertRequestDto clientDto) {
        return ClientResponseDto.toResponseDto(clientController.insert(clientDto.toEntity()));
    }

}
