package com.fiap.burger.web.controller;

import com.fiap.burger.usecases.product.ProductService;
import com.fiap.burger.web.dto.product.response.ProductListResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/products")
  public List<ProductListResponseDto> products(ProductService productService) {
    return productService
        .findAll()
        .stream()
        .map(ProductListResponseDto::toResponseDto)
        .collect(Collectors.toList());
  }
}
