package com.example.backend_store.service;

import com.example.backend_store.entity.Role;

public interface RoleService {
    Role createRole(String roleName);

    void deleteRole(String roleName);

    void updateRole(String oldRoleName, String newRoleName);

    boolean roleExists(String roleName);
}
