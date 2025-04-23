package com.example.backend_store.service.imple;

import com.example.backend_store.entity.Role;
import com.example.backend_store.repository.RolRepository;
import com.example.backend_store.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RoleService {
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Role createRole(String roleName) {
        if (roleName.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null");
        } else if (rolRepository.findByName(roleName).isPresent()) {
            throw new IllegalArgumentException("Role already exists");
        } else {
            Role role = new Role();
            role.setName(roleName);
            return rolRepository.save(role);
        }
    }

    @Override
    public void deleteRole(String roleName) {

    }

    @Override
    public void updateRole(String oldRoleName, String newRoleName) {

    }

    @Override
    public boolean roleExists(String roleName) {
        if (roleName.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null");
        } else {
            return rolRepository.findByName(roleName).isPresent();
        }
    }
}
