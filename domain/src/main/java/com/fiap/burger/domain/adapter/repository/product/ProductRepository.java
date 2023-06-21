package com.fiap.burger.domain.adapter.repository.product;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import java.util.List;

public interface ProductRepository {

    Product findById(Long id);
    List<Product> findAll();

    List<Product> findAllBy(Category category);
    Product save(Product product);
}
