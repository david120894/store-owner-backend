package com.example.backend_store.product.inventory.entity;

import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.store.entity.Store;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
}
