package com.chasermanager.services;

import com.chasermanager.domain.models.Item;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.domain.models.User;
import org.jsoup.select.Elements;

import java.util.List;

public interface ItemService {
    Item create(Item item, Long userId);
}
