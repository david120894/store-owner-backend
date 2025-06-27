package com.example.backend_store.product.inventory.entity;

import com.example.backend_store.product.presentations.entity.Presentation;
import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.store.entity.Store;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean deleted=false;
    private Double priceInitial;
    private Double priceCurrent;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;
    @ManyToOne()
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;
}
