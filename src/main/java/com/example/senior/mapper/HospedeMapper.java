package com.example.senior.mapper;

import com.example.senior.dto.HospedeDTO;
import com.example.senior.entity.HospedeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospedeMapper extends BaseEntityMapper<HospedeEntity, HospedeDTO>{

    @Override
    HospedeDTO toDTO(HospedeEntity entity);

    @Override
    HospedeEntity toEntity(HospedeDTO dto);

    @Override
    List<HospedeDTO> toDTO(List<HospedeEntity> hospedeEntities);
}
