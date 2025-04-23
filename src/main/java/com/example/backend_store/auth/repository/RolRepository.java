package com.example.backend_store.auth.repository;

import com.example.backend_store.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
