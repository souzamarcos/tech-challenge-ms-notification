package com.fiap.burger.domain.service;

import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> findAll() {
        return repository.findlAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }
}
