package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;

import java.util.List;

public interface ProductUseCase {
    Product findById(Long id);

    List<Product> findAll();

    List<Product> findAllBy(Category category);

    Product insert(Product product);

    Product update(Product product);

    void deleteBy(Long id);
}
