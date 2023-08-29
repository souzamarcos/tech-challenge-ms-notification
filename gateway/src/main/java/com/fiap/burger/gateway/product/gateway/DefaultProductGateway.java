package com.fiap.burger.gateway.product.gateway;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.gateway.product.dao.ProductDAO;
import com.fiap.burger.gateway.product.model.ProductJPA;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultProductGateway implements ProductGateway {
    @Autowired
    ProductDAO productDAO;

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id).map(ProductJPA::toEntity).orElse(null);
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        return productDAO.findAllByIdInAndDeletedAtNull(ids).stream().map(ProductJPA::toEntity).collect(Collectors.toList());
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

