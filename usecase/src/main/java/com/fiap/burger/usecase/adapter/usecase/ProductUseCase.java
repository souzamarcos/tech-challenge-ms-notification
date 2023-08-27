package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.entity.product.Category;
import com.fiap.burger.entity.entity.product.Product;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;

import java.util.List;

public interface ProductUseCase {
    Product findById(ProductGateway gateway, Long id);
    List<Product> findAll(ProductGateway gateway);
    List<Product> findAllBy(ProductGateway gateway, Category category);
    Product insert(ProductGateway gateway, Product product);
    Product update(ProductGateway gateway, Product product);
    void deleteBy(ProductGateway gateway, Long id);
}
