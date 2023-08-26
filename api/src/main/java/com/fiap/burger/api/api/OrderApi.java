package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.order.request.OrderInsertRequestDto;
import com.fiap.burger.api.dto.order.request.OrderUpdateStatusRequestDto;
import com.fiap.burger.api.dto.order.response.ListOrderResponseDto;
import com.fiap.burger.api.dto.order.response.OrderResponseDto;
import com.fiap.burger.controller.adapter.api.OrderController;
import com.fiap.burger.domain.entities.order.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@Tag(name = "pedido", description = "API responsável pelo controle de pedidos.")
public class OrderApi {

    @Autowired
    private OrderController controller;

    @Operation(summary = "Criar pedido", description = "Criação de um novo pedido", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Pedido inválido")})
    @PostMapping()
    public ListOrderResponseDto insert(@RequestBody OrderInsertRequestDto orderDto) {
        // TODO retornar items do pedido inserido
        return ListOrderResponseDto.toResponseDto(controller.insert(orderDto.toEntity()));
    }

    @Operation(summary = "Consultar pedido", description = "Consultar pedidos", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping("/{orderId}")
    public OrderResponseDto findById(@PathVariable Long orderId) {
        return OrderResponseDto.toResponseDto(controller.findById(orderId));
    }

    @Operation(summary = "Atualizar status do pedido", description = "Atualizar status de um pedido", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Requisição inválida")})
    @PostMapping("/{orderId}/status")
    public ListOrderResponseDto updateStatus(@PathVariable Long orderId, @RequestBody OrderUpdateStatusRequestDto orderDto) {
        return ListOrderResponseDto.toResponseDto(controller.updateStatus(orderId, orderDto.newStatus()));
    }

    @Operation(summary = "Listar pedidos", description = "Listar pedidos", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping()
    public List<ListOrderResponseDto> findAll(@RequestParam @Nullable OrderStatus status) {
        return controller.findAllBy(status).stream().map(ListOrderResponseDto::toResponseDto).collect(Collectors.toList());
    }

    @Operation(summary = "Listar pedidos em progresso", description = "Listar pedidos em progresso. Status igual a EM_PREPARAÇÂO ou PRONTO.", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping("/in-progress")
    public List<ListOrderResponseDto> findAllInProgress() {
        return controller.findAllInProgress().stream().map(ListOrderResponseDto::toResponseDto).collect(Collectors.toList());
    }

    @Operation(summary = "Realizar pagamento do pedido", description = "Realizar pagamento do pedido", tags = {"pedido"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Requisição inválida")})
    @PostMapping("/{orderId}/checkout")
    public ListOrderResponseDto checkout(@PathVariable Long orderId) {
        return ListOrderResponseDto.toResponseDto(controller.checkout(orderId));
    }
}
