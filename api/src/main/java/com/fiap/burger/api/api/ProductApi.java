package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.api.dto.product.request.ProductUpdateRequestDto;
import com.fiap.burger.api.dto.product.response.ProductResponseByIdDto;
import com.fiap.burger.api.dto.product.response.ProductResponseDto;
import com.fiap.burger.controller.adapter.api.ProductController;
import com.fiap.burger.entity.product.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Tag(name = "produto", description = "API responsável pelo controle de produtos disponíveis para realização de pedidos.")
public class ProductApi {
    @Autowired
    private ProductController controller;

    @Operation(summary = "Listar produtos", description = "Listagem dos produtos cadastrados", tags = {"produto"})
    @GetMapping()
    public List<ProductResponseDto> list(@RequestParam @Nullable Category category) {
        return controller
            .list(category)
            .stream()
            .map(ProductResponseDto::toResponseDto)
            .collect(Collectors.toList());
    }

    @Operation(summary = "Consultar produto", description = "Consultar um produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{productId}")
    public ProductResponseByIdDto findById(@PathVariable Long productId) {
        return ProductResponseByIdDto.toResponseDto(controller.findById(productId));
    }

    @Operation(summary = "Criar produto", description = "Criação de um novo produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido")
    })
    @PostMapping()
    public ProductResponseDto insert(@RequestBody ProductInsertRequestDto productDto) {
        return ProductResponseDto.toResponseDto(controller.insert(productDto.toEntity()));
    }

    @Operation(summary = "Atualizar produto", description = "Atualizar um produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping()
    public ProductResponseDto update(@RequestBody ProductUpdateRequestDto productDto) {
        return ProductResponseDto.toResponseDto(controller.update(productDto.toEntity()));
    }

    @Operation(summary = "Deletar produto", description = "Deletar um produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{productId}")
    public String deleteBy(@PathVariable Long productId) {
        return controller.deleteBy(productId);
    }
}
