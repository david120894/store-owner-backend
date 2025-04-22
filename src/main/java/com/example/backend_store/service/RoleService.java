package com.example.backend_store.service;

public interface RoleService {
    void createRole(String roleName);

    void deleteRole(String roleName);

    void updateRole(String oldRoleName, String newRoleName);

    boolean roleExists(String roleName);
}
