package com.fiap.burger.web.controller;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.web.dto.order.request.OrderInsertRequestDto;
import com.fiap.burger.web.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.web.dto.product.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "pedido", description = "API responsável pelo controle de pedidos.")

public class OrderController {
        @Operation(summary = "Criar pedido", description = "Criação de um novo pedido", tags = {"pedido"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "400", description = "Pedido inválido")
        })
        @PostMapping("/orders")
        public String insert(@RequestBody OrderInsertRequestDto orderDto) {

            return "Mestre ensinandooooo";
        }
    }
