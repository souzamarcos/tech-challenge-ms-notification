package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.adapter.usecase.ProductUseCase;
import com.fiap.burger.usecase.misc.exception.DeletedProductException;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;
import com.fiap.burger.usecase.misc.exception.ProductNotFoundException;

import java.util.List;

import static com.fiap.burger.usecase.misc.validation.ValidationUtils.*;

public class DefaultProductUseCase implements ProductUseCase {

    private final ProductGateway productGateway;

    public DefaultProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product findById(Long id) {
        return productGateway.findById(id);
    }

    public List<Product> findAll() {
        return productGateway.findAll();
    }

    public List<Product> findAllBy(Category category) {
        return productGateway.findAllBy(category);
    }

    public Product insert(Product product) {
        validateProductToInsert(product);
        return productGateway.save(product);
    }

    public Product update(Product product) {
        validateProductToUpdate(product);
        return productGateway.save(product);
    }

    public void deleteBy(Long id) {
        var product = productGateway.findById(id);
        if (product == null) throw new ProductNotFoundException(id);
        if (product.getDeletedAt() != null) throw new DeletedProductException();
        productGateway.deleteBy(id);
    }

    private void validateProductToInsert(Product product) {
        if (product.getId() != null) throw new InvalidAttributeException("Product should not have defined id", "id");
        validateProduct(product);
    }

    private void validateProductToUpdate(Product product) {
        var persistedProduct = productGateway.findById(product.getId());
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
