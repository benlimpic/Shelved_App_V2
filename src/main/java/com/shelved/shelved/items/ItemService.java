package com.shelved.shelved.items;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Get All Items
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //Get Item
    public Item getItemById(Integer id) {
        Optional<Item> item = itemRepository.findById(id);

        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    //Post Item
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    //Put Item
    public Item updateItem(Item updatedItem) {
        Optional<Item> optionalItem = itemRepository.findById(updatedItem.getId());

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
            item.setImageUrl(updatedItem.getImageUrl());
            return itemRepository.save(item);
        }
        return null;
    }

    //Delete Item
    public void deleteItem(Integer id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
        }
    }
}
