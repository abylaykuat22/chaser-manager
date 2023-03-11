package com.chasermanager.domain.utils;

import com.chasermanager.domain.models.Item;
import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.User;

public class ItemBuilder {
    public static Item buildItem(String name, String price, String description, String img, String link, Source source, User user) {
        return Item.builder()
                .name(name)
                .price(price)
                .description(description)
                .img(img)
                .link(link)
                .source(source)
                .user(user)
                .build();
    }
}
