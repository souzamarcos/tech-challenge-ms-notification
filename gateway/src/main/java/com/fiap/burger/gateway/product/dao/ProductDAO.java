package com.fiap.burger.gateway.product.dao;

import com.fiap.burger.entity.entity.product.Category;
import com.fiap.burger.gateway.product.model.ProductJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDAO extends JpaRepository<ProductJPA, Long> {

    List<ProductJPA> findAllByDeletedAtNull();

    List<ProductJPA> findAllByCategoryAndDeletedAtNull(Category category);

    List<ProductJPA> findAllByIdInAndDeletedAtNull(List<Long> ids);

    @Modifying
    @Query("update ProductJPA p set p.deletedAt = CURRENT_TIMESTAMP where p.id = :id")
    void deleteById(@Param("id") Long id);
}
