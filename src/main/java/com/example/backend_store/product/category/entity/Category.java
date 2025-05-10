package com.example.backend_store.product.category.entity;

import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.store.entity.Store;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private Boolean deleted=false;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;
    @ManyToOne()
    @JoinColumn(name = "store_id")
    private Store store;
    @OneToMany(mappedBy = "productCategory",
    cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
