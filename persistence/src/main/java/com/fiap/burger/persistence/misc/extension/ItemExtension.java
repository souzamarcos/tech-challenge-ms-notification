package com.fiap.burger.persistence.misc.extension;

import com.fiap.burger.entities.item.Item;
import com.fiap.burger.persistence.item.model.ItemJPA;

public class ItemExtension {
    public static ItemJPA toJPA(Item item) {
      return new ItemJPA(item.id(), item.type(), item.name(), item.description());
    }
}
