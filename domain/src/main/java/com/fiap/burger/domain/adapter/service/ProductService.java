package com.fiap.burger.domain.adapter.service;

import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;

import java.util.List;

public interface ProductService {
    Product findById(ProductRepository repository, Long id);
    List<Product> findAll(ProductRepository repository);
    List<Product> findAllBy(ProductRepository repository, Category category);
    Product insert(ProductRepository repository, Product product);
    Product update(ProductRepository repository, Product product);
    void deleteBy(ProductRepository repository, Long id);

}
