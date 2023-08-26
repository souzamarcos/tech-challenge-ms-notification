package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.service.ProductService;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.misc.exception.DeletedProductException;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import com.fiap.burger.domain.misc.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fiap.burger.domain.misc.ValidationUtils.validateNotBlank;
import static com.fiap.burger.domain.misc.ValidationUtils.validateNotNull;
import static com.fiap.burger.domain.misc.ValidationUtils.validatePositiveNotZero;

@Service
public class DefaultProductService implements ProductService {


    public Product findById(ProductRepository repository, Long id) {
        return repository.findById(id);
    }

    public List<Product> findAll(ProductRepository repository) {
        return repository.findAll();
    }

    public List<Product> findAllBy(ProductRepository repository, Category category) {
        return repository.findAllBy(category);
    }

    public Product insert(ProductRepository repository, Product product) {
        validateProductToInsert(product);
        return repository.save(product);
    }

    public Product update(ProductRepository repository, Product product) {
        validateProductToUpdate(repository, product);
        return repository.save(product);
    }

    public void deleteBy(ProductRepository repository, Long id) {
        var product = repository.findById(id);
        if (product == null) throw new ProductNotFoundException(id);
        if (product.getDeletedAt() != null) throw new DeletedProductException();
        repository.deleteBy(id);
    }

    private void validateProductToInsert(Product product) {
        if (product.getId() != null) throw new InvalidAttributeException("Product should not have defined id", "id");
        validateProduct(product);
    }

    private void validateProductToUpdate(ProductRepository repository, Product product) {
        var persistedProduct = repository.findById(product.getId());
        if (persistedProduct == null) throw new ProductNotFoundException();
        validateProduct(product);
    }

    private void validateProduct(Product product) {
        validateNotNull(product.getCategory(), "category");
        validateNotNull(product.getName(), "name");
        validateNotBlank(product.getName(), "name");
        validateNotNull(product.getDescription(), "description");
        validateNotBlank(product.getDescription(), "description");
        validateNotNull(product.getValue(), "value");
        validatePositiveNotZero(product.getValue(), "value");
    }
}
