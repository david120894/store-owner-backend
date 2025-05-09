package com.example.backend_store.store.repository;

import com.example.backend_store.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByName(String name);
}
