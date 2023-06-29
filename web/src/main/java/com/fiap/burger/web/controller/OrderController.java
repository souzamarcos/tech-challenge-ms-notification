package com.fiap.burger.web.controller;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.service.OrderService;
import com.fiap.burger.domain.service.ProductService;
import com.fiap.burger.web.dto.order.request.OrderInsertRequestDto;
import com.fiap.burger.web.dto.order.response.OrderResponseDto;
import com.fiap.burger.web.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.web.dto.product.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "pedido", description = "API responsável pelo controle de pedidos.")
public class OrderController {

    @Autowired
    OrderService service;

    @Operation(summary = "Criar pedido", description = "Criação de um novo pedido", tags = {"pedido"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Pedido inválido")
    })
    @PostMapping("/orders")
    public OrderResponseDto insert(@RequestBody OrderInsertRequestDto orderDto) {
        return OrderResponseDto.toResponseDto(service.insert(orderDto.toEntity()));
    }

    @Operation(summary = "Listar pedidos", description = "Listar pedidos não deletados", tags = {"pedido"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Request inválida")
    })
    @GetMapping("/orders")
    public List<OrderResponseDto> findAll() {
        // TODO verificar se precisamos order por id de forma descrescente
        return service.findAll().stream().map(OrderResponseDto::toResponseDto).collect(Collectors.toList());
    }
}
