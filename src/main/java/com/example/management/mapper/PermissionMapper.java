package com.example.management.mapper;

import com.example.management.dto.PermissionDTO;
import common.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionDTO toDTO(Permission permission);
    Permission toEntity(PermissionDTO permissionDTO);
    List<PermissionDTO> toDTOList(List<Permission> permissions);
}
