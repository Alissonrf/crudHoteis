package com.example.senior.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;


public interface BaseEntityMapper<K, T> {
    @Named("toDTO")
    T toDTO(K entity);

    K toEntity(T dto);

    K toEntity(T dto, @MappingTarget K entity);

    @IterableMapping(qualifiedByName = "toDTO")
    List<T> toDTO(List<K> entity);

}
