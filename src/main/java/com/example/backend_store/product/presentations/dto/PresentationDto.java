package com.example.backend_store.product.presentations.dto;

import com.example.backend_store.product.product.dto.ProductDto;
import com.example.backend_store.product.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresentationDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String unit;
    private String unitType;
    private Integer stockQuantity;
    private ProductDto product;

}
