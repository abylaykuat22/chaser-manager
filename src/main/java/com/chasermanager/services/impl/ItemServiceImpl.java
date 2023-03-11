package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Item;
import com.chasermanager.repositories.ItemRepository;
import com.chasermanager.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Item create(Item item) {
        boolean isExist = itemRepository.existsByNameAndPriceAndLink(item.getName(), item.getPrice(), item.getLink());
        if (!isExist) return itemRepository.save(item);
        return null;
    }
}
