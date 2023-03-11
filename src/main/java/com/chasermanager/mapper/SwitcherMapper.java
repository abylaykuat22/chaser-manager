package com.chasermanager.mapper;

import com.chasermanager.domain.dto.SwitcherView;
import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.models.Switcher;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SwitcherMapper {
    SwitcherMapper INSTANCE = Mappers.getMapper(SwitcherMapper.class);

    @Mapping(source = "url.link", target = "url")
    SwitcherView toView(Switcher entity);

    default Periodicity mapPeriodicity (long periodicity) {
        return (Periodicity) Periodicity.mapToWords(periodicity);
    }
}
