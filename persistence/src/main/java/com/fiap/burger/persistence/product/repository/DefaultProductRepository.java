package com.fiap.burger.persistence.product.repository;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.persistence.product.dao.ProductDAO;
import com.fiap.burger.persistence.product.model.ProductJPA;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultProductRepository implements ProductRepository {
    @Autowired
    ProductDAO productDAO;

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id).map(ProductJPA::toEntity).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAllByDeletedAtNull().stream().map(ProductJPA::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllBy(Category category) {
        return productDAO.findAllByCategoryAndDeletedAtNull(category)
            .stream()
            .map(ProductJPA::toEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        return productDAO.save(ProductJPA.toJPA(product)).toEntity();
    }

    @Override
    public void deleteBy(Long id) {
        productDAO.deleteById(id);
    }
}
