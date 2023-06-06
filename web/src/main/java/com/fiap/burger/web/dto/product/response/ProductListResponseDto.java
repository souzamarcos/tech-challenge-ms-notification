package com.fiap.burger.web.dto.product.response;

import com.fiap.burger.entities.product.Category;
import com.fiap.burger.entities.product.Product;

public record ProductListResponseDto(Long id, Category category, String name, String description) {
  public static ProductListResponseDto toResponseDto(Product product) {
    return new ProductListResponseDto(product.id(), product.category(), product.name(), product.description());
  }
}