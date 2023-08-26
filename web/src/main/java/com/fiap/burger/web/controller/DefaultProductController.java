package com.fiap.burger.web.controller;

import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.adapter.service.ProductService;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.misc.exception.ProductNotFoundException;
import com.fiap.burger.web.adapter.api.product.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultProductController implements ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;
    @Override
    public List<Product> list(Category category) {
        if (category == null) {
            return service
                    .findAll(repository);
        } else {
            return service
                    .findAllBy(repository, category);
        }
    }

    @Override
    public Product findById(Long productId) {
        var persistedProduct = service.findById(repository, productId);
        if (persistedProduct == null) throw new ProductNotFoundException();
        return persistedProduct;
    }

    @Override
    public Product insert(Product product) {
        return service.insert(repository, product);
    }

    @Override
    public Product update(Product product) {
        return service.update(repository, product);
    }

    @Override
    public String deleteBy(Long productId) {
        service.deleteBy(repository, productId);
        return "Product has been successfully deleted.";
    }
}
