package com.chasermanager.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SwitcherMapper {
    SwitcherMapper INSTANCE = Mappers.getMapper(SwitcherMapper.class);

}
