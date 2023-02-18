package com.chasermanager.services.impl;

import com.chasermanager.domain.dto.ItemCreate;
import com.chasermanager.domain.models.Item;
import com.chasermanager.mapper.ItemMapper;
import com.chasermanager.repositories.ItemRepository;
import com.chasermanager.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Item create(Item item, Long userId) {
        List<Item> items = itemRepository.findAllByUserId(userId);
        if (!isContains(items, item)){
            return itemRepository.save(item);
        }
        return null;
    }

    private boolean isContains(List<Item> items, Item item) {
        List<ItemCreate> createList = ItemMapper.INSTANCE.toCreateList(items);
        ItemCreate create = ItemMapper.INSTANCE.toCreate(item);
        return createList.contains(create);
    }

}
