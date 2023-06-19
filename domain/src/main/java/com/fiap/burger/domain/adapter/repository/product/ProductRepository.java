package com.fiap.burger.domain.adapter.repository.product;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findlAll();

    List<Product> findlAllBy(Category category);
    Product save(Product product);
}
