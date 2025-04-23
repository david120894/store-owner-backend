package com.example.backend_store.service;

import java.util.List;

import com.example.backend_store.entity.Role;

public interface RoleService {
    List<Role> getAllRoles();
    Role createRole(String name);

    void deleteRole(String name);

    void updateRole(String oldRoleName, String newRoleName);

    boolean roleExists(String roleName);
}
