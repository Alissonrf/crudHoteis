package com.example.senior.mapper;

import com.example.senior.dto.ReservaDTO;
import com.example.senior.entity.ReservaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper extends BaseEntityMapper<ReservaEntity, ReservaDTO>{

    @Override
    @Named("toDTO")
    ReservaDTO toDTO(ReservaEntity entity);

    @Override
    @Named("toEntity")
    ReservaEntity toEntity(ReservaDTO dto);

    @Override
    List<ReservaDTO> toDTO(List<ReservaEntity> reservaEntities);
}
