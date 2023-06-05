package com.fiap.burger.persistence.item.repository;

import com.fiap.burger.entities.item.Item;
import com.fiap.burger.persistence.item.dao.ItemDAO;
import com.fiap.burger.persistence.item.model.ItemJPA;
import com.fiap.burger.persistence.misc.extension.ItemExtension;
import com.fiap.burger.usecases.item.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultItemRepository implements ItemRepository {
  @Autowired
  private ItemDAO itemDAO;

  @Override
  public List<Item> findlAll() {
    return itemDAO.findAll().stream().map(ItemJPA::toEntity).collect(Collectors.toList());
  }

  @Override
  public Item save(Item item) {
    return itemDAO.save(ItemExtension.toJPA(item)).toEntity();
  }
}
