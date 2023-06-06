package com.fiap.burger.usecases.product.repository;

import com.fiap.burger.entities.product.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findlAll();
    Product save(Product product);
}
