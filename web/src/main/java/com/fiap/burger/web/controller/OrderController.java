package com.fiap.burger.web.controller;

import com.fiap.burger.domain.misc.exception.OrderNotFoundException;
import com.fiap.burger.domain.service.OrderService;
import com.fiap.burger.web.dto.order.request.OrderInsertRequestDto;
import com.fiap.burger.web.dto.order.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "pedido", description = "API responsável pelo controle de pedidos.")
public class OrderController {

    @Autowired
    OrderService service;

    @Operation(summary = "Criar pedido", description = "Criação de um novo pedido", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Pedido inválido")})
    @PostMapping("/orders")
    public OrderResponseDto insert(@RequestBody OrderInsertRequestDto orderDto) {
        return OrderResponseDto.toResponseDto(service.insert(orderDto.toEntity()));
    }

    @Operation(summary = "Consultar pedido", description = "Consultar pedidos", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping("/orders/{orderId}")
    public OrderResponseDto findById(@PathVariable Long orderId) {
        var persistedOrder = service.findById(orderId);
        if (persistedOrder == null) throw new OrderNotFoundException(orderId);
        return OrderResponseDto.toResponseDto(persistedOrder);
    }

    @Operation(summary = "Listar pedidos", description = "Listar pedidos", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping("/orders")
    public List<OrderResponseDto> findAll() {
        // TODO verificar se precisamos order por id de forma descrescente
        return service.findAll().stream().map(OrderResponseDto::toResponseDto).collect(Collectors.toList());
    }
}
