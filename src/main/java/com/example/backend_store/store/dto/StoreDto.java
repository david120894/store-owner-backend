package com.example.backend_store.store.dto;

import com.example.backend_store.product.category.entity.Category;
import com.example.backend_store.product.inventory.entity.Inventory;
import com.example.backend_store.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private String address;
    private String description;
    private LocalDateTime created;
}
