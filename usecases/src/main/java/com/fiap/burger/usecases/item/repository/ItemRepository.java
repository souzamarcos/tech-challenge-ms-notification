package com.fiap.burger.usecases.item.repository;

import com.fiap.burger.entities.item.Item;
import java.util.List;

public interface ItemRepository {
    List<Item> findlAll();
    Item save(Item item);
}
