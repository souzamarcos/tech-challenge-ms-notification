package com.fiap.burger.usecases.item;

import com.fiap.burger.entities.item.Item;
import com.fiap.burger.usecases.item.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findlAll();
    }

    public Item save(Item item) {
        return repository.save(item);
    }
}
