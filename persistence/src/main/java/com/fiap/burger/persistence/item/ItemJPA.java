package com.fiap.burger.persistence.item;

import com.fiap.burger.entities.item.Item;
import com.fiap.burger.entities.item.ItemType;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity(name = "item")
public record ItemJPA(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ItemType type,
    @Column(nullable = false)
    String name,
    @Column(nullable = false)
    String description
) {

    public Item toEntity(){
        return new Item(id, type, name, description);
    }
}

