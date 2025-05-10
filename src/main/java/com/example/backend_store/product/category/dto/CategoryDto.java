package com.example.backend_store.product.category.dto;

import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private LocalDateTime created;
    private StoreDto store;
}
