package com.example.backend_store.auth.service.impl;

import com.example.backend_store.auth.dto.RolDto;
import com.example.backend_store.auth.entity.Role;
import com.example.backend_store.auth.exceptions.BadRequestException;
import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.auth.exceptions.NotFoundException;
import com.example.backend_store.auth.repository.RolRepository;
import com.example.backend_store.auth.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RoleService {
    @Autowired
    private RolRepository rolRepository;


    @Override
    public RolDto createRole(RolDto  rol) {
        if (rol.getName().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null");
        } else if (rolRepository.findByName(rol.getName()).isPresent()) {
            throw new IllegalArgumentException("Role already exists");
        } else {
            Role role = new Role();
            role.setName(rol.getName());        
            Role roles=rolRepository.save(role);
            RolDto rolDto = new RolDto();
            rolDto.setId(roles.getId());
            rolDto.setName(roles.getName());
            return rolDto;
        }
    }

    @Override
    public void deleteRole(String roleName) {

    }

    @Override
    public RolDto updateRole(Long idRol, RolDto rol) {

        if (idRol == null || idRol == 0) {
            throw new BadRequestException("Role id cannot be null");
        }
        Role role = rolRepository.findById(idRol)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + idRol));
        if (rolRepository.findByName(rol.getName()).isPresent()) {
            throw new ConflictException("Role already exists");
        }
        role.setName(rol.getName());
        Role aux = rolRepository.save(role);
        RolDto rolDto = new RolDto();
        rolDto.setId(aux.getId());
        rolDto.setName(aux.getName());
        return rolDto;
    }

    @Override
    public boolean roleExists(String roleName) {
        if (roleName.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null");
        } else {
            return rolRepository.findByName(roleName).isPresent();
        }
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return rolRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException("No roles found");
        }
    }
}
