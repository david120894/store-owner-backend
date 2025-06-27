package com.example.backend_store.auth.service;

import java.util.List;

import com.example.backend_store.auth.dto.RolDto;
import com.example.backend_store.auth.entity.Role;

public interface RoleService {

    List<Role> getAllRoles();

    RolDto createRole(RolDto rol);

    void deleteRole(String name);

    RolDto updateRole(Long id, RolDto newRoleName);

    boolean roleExists(String roleName);
}
