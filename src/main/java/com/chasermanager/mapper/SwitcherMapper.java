package com.chasermanager.mapper;

import com.chasermanager.domain.dto.SwitcherCreate;
import com.chasermanager.domain.models.Switcher;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SwitcherMapper {
    SwitcherMapper INSTANCE = Mappers.getMapper(SwitcherMapper.class);

    SwitcherCreate toCreate(Switcher entity);
    List<SwitcherCreate> toCreateList(List<Switcher> entityList);
}
