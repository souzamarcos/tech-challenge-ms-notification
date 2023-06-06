package com.fiap.burger.persistence.product.dao;

import com.fiap.burger.persistence.product.model.ProductJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<ProductJPA, Long> {}
