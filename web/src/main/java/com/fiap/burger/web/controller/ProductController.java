package com.fiap.burger.web.controller;

import com.fiap.burger.entities.product.Product;
import com.fiap.burger.usecases.product.ProductService;
import com.fiap.burger.web.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.web.dto.product.response.ProductResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductResponseDto> list() {
        return productService
            .findAll()
            .stream()
            .map(ProductResponseDto::toResponseDto)
            .collect(Collectors.toList());
    }

    @PostMapping("/products")
    public ProductResponseDto insert(@RequestBody ProductInsertRequestDto productDto) {
        Product persistedProduct = productService.save(productDto.toEntity());
        return ProductResponseDto.toResponseDto(persistedProduct);
    }
}
