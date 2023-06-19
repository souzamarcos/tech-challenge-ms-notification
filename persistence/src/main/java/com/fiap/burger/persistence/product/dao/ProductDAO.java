package com.fiap.burger.persistence.product.dao;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.persistence.product.model.ProductJPA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<ProductJPA, Long> {

    List<ProductJPA> findAllByDeletedAtNull();
    List<ProductJPA> findAllByCategoryAndDeletedAtNull(Category category);
}
