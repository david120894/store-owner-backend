package com.example.backend_store.product.category.service;

import com.example.backend_store.product.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    CategoryDto delete(Long id);
    List<CategoryDto> getCategoryByStoreId(Long storeId);

}
