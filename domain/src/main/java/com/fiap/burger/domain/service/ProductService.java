package com.fiap.burger.domain.service;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.misc.exception.BlankAttributeException;
import com.fiap.burger.domain.misc.exception.NegativeOrZeroValueException;
import com.fiap.burger.domain.misc.exception.NullAttributeException;
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

    public List<Product> findAllBy(Category category) {
        return repository.findlAllBy(category);
    }

    public Product save(Product product) {
        validateProduct(product);
        return repository.save(product);
    }

    public void validateProduct(Product product) {
        if (product.getCategory() == null) throw new NullAttributeException("category");
        if (product.getName() == null) throw new NullAttributeException("name");
        if (product.getName().isBlank()) throw new BlankAttributeException("name");
        if (product.getDescription() == null) throw new NullAttributeException("description");
        if (product.getDescription().isBlank()) throw new BlankAttributeException("description");
        if (product.getValue() == null) throw new NullAttributeException("value");
        if (product.getValue() <= 0) throw new NegativeOrZeroValueException("value");
    }
}
