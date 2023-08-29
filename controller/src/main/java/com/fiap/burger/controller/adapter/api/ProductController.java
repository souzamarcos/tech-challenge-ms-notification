package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;

import java.util.List;

public interface ProductController {
    List<Product> list(Category category);

    Product findById(Long productId);

    Product insert(Product product);

    Product update(Product product);

    String deleteBy(Long productId);

}