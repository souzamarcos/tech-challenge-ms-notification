package com.fiap.burger.persistence.product.dao;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.persistence.product.model.ProductJPA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductDAO extends JpaRepository<ProductJPA, Long> {

    List<ProductJPA> findAllByDeletedAtNull();

    List<ProductJPA> findAllByCategoryAndDeletedAtNull(Category category);

    List<ProductJPA> findAllByIdInAndDeletedAtNull(List<Long> ids);

    @Modifying
    @Query("update ProductJPA p set p.deletedAt = CURRENT_TIMESTAMP where p.id = :id")
    void deleteById(@Param("id") Long id);
}
