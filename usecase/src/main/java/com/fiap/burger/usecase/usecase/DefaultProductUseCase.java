package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.adapter.usecase.ProductUseCase;
import com.fiap.burger.usecase.misc.exception.DeletedProductException;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;
import com.fiap.burger.usecase.misc.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.burger.usecase.misc.validation.ValidationUtils.*;

@Service
public class DefaultProductUseCase implements ProductUseCase {

    public Product findById(ProductGateway repository, Long id) {
        return repository.findById(id);
    }

    public List<Product> findAll(ProductGateway repository) {
        return repository.findAll();
    }

    public List<Product> findAllBy(ProductGateway repository, Category category) {
        return repository.findAllBy(category);
    }

    public Product insert(ProductGateway repository, Product product) {
        validateProductToInsert(product);
        return repository.save(product);
    }

    public Product update(ProductGateway repository, Product product) {
        validateProductToUpdate(repository, product);
        return repository.save(product);
    }

    public void deleteBy(ProductGateway repository, Long id) {
        var product = repository.findById(id);
        if (product == null) throw new ProductNotFoundException(id);
        if (product.getDeletedAt() != null) throw new DeletedProductException();
        repository.deleteBy(id);
    }

    private void validateProductToInsert(Product product) {
        if (product.getId() != null) throw new InvalidAttributeException("Product should not have defined id", "id");
        validateProduct(product);
    }

    private void validateProductToUpdate(ProductGateway repository, Product product) {
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
