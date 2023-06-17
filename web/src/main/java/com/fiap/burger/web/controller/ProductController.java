package com.fiap.burger.web.controller;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.service.ProductService;
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
@Tag(name = "produto", description = "API responsável pelo controle de produtos disponíveis para realização de pedidos.")
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Listar produtos", description = "Listagem dos produtos cadastrados", tags = { "produto" })
    @GetMapping("/products")
    public List<ProductResponseDto> list() {
        return productService
            .findAll()
            .stream()
            .map(ProductResponseDto::toResponseDto)
            .collect(Collectors.toList());
    }

    @Operation(summary = "Criar produto", description = "Criação de um novo produto", tags = { "produto" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido")
    })
    @PostMapping("/products")
    public ProductResponseDto insert(@RequestBody ProductInsertRequestDto productDto) {
        Product persistedProduct = productService.save(productDto.toEntity());
        return ProductResponseDto.toResponseDto(persistedProduct);
    }
}
