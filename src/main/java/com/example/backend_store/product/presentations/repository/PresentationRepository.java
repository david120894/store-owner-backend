package com.example.backend_store.product.presentations.repository;

import com.example.backend_store.product.presentations.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation,Long> {
    boolean existsByName(String name);
}
