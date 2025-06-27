package com.example.backend_store.product.product.dto;

import com.example.backend_store.product.category.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String imageUrl;
    private LocalDateTime created;
    private CategoryDto categoryDto;
}
