package com.example.management.service;

import com.example.management.dto.PermissionDTO;
import com.example.management.mapper.PermissionMapper;
import common.model.Permission;
import com.example.management.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public List<PermissionDTO> getAllPermissions() {
        return permissionMapper.toDTOList(permissionRepository.findAll());
    }

    public PermissionDTO getPermissionById(Integer id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        return permission.map(permissionMapper::toDTO).orElse(null);
    }

    public PermissionDTO createPermission(PermissionDTO dto) {
        Permission permission = permissionMapper.toEntity(dto);
        return permissionMapper.toDTO(permissionRepository.save(permission));
    }

    public PermissionDTO updatePermission(Integer id, PermissionDTO dto) {
        return permissionRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    return permissionMapper.toDTO(permissionRepository.save(existing));
                }).orElse(null);
    }

    public boolean deletePermission(Integer id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
            return true;
        }
        return false;
}
}