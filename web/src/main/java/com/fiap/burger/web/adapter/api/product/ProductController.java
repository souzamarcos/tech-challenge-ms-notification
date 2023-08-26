package com.fiap.burger.web.adapter.api.product;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;

import java.util.List;

public interface ProductController {
    List<Product> list(Category category);
    Product findById(Long productId);
    Product insert(Product product);
    Product update(Product product);
    String deleteBy(Long productId);

}
