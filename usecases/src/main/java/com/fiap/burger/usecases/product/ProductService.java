package com.fiap.burger.usecases.product;

import com.fiap.burger.entities.product.Product;
import com.fiap.burger.usecases.product.repository.ProductRepository;
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
