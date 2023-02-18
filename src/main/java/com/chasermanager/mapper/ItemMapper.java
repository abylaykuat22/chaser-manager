package com.chasermanager.mapper;

import com.chasermanager.domain.dto.ItemCreate;
import com.chasermanager.domain.models.Item;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemCreate toCreate(Item entity);
    List<ItemCreate> toCreateList(List<Item> entityList);
}
