package com.fiap.burger.persistence.item.dao;

import com.fiap.burger.persistence.item.model.ItemJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemJPA, Long> {}
