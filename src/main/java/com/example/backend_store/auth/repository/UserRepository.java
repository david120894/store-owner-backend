package com.example.backend_store.auth.repository;

import com.example.backend_store.auth.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // User findByUsername(String username);

    Boolean existsByUsername(String username);

}
