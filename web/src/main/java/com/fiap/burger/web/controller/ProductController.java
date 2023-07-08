package com.fiap.burger.web.controller;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.misc.exception.ProductNotFoundException;
import com.fiap.burger.domain.service.ProductService;
import com.fiap.burger.web.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.web.dto.product.request.ProductUpdateRequestDto;
import com.fiap.burger.web.dto.product.response.ProductResponseByIdDto;
import com.fiap.burger.web.dto.product.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "produto", description = "API responsável pelo controle de produtos disponíveis para realização de pedidos.")
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(summary = "Listar produtos", description = "Listagem dos produtos cadastrados", tags = {"produto"})
    @GetMapping()
    public List<ProductResponseDto> list(@RequestParam @Nullable Category category) {
        if (category == null) {
            return service
                .findAll()
                .stream()
                .map(ProductResponseDto::toResponseDto)
                .collect(Collectors.toList());
        } else {
            return service
                .findAllBy(category)
                .stream()
                .map(ProductResponseDto::toResponseDto)
                .collect(Collectors.toList());
        }
    }

    @Operation(summary = "Consultar produto", description = "Consultar um produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{productId}")
    public ProductResponseByIdDto findById(@PathVariable Long productId) {
        var persistedProduct = service.findById(productId);
        if (persistedProduct == null) throw new ProductNotFoundException();
        return ProductResponseByIdDto.toResponseDto(persistedProduct);
    }

    @Operation(summary = "Criar produto", description = "Criação de um novo produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido")
    })
    @PostMapping()
    public ProductResponseDto insert(@RequestBody ProductInsertRequestDto productDto) {
        Product persistedProduct = service.insert(productDto.toEntity());
        return ProductResponseDto.toResponseDto(persistedProduct);
    }

    @Operation(summary = "Atualizar produto", description = "Atualizar um produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping()
    public ProductResponseDto update(@RequestBody ProductUpdateRequestDto productDto) {
        Product persistedProduct = service.update(productDto.toEntity());
        return ProductResponseDto.toResponseDto(persistedProduct);
    }

    @Operation(summary = "Deletar produto", description = "Deletar um produto", tags = {"produto"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto inválido")
    })
    @DeleteMapping("/{productId}")
    public ProductResponseDto deleteBy(@PathVariable Long productId) {
        service.deleteBy(productId);
        return null;
    }
}
