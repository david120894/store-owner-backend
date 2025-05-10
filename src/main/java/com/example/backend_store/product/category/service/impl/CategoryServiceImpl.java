package com.example.backend_store.product.category.service.impl;

import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.product.category.dto.CategoryDto;
import com.example.backend_store.product.category.entity.Category;
import com.example.backend_store.product.category.repository.CategoryRepository;
import com.example.backend_store.product.category.service.CategoryService;
import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = this.categoryRepository.findByDeletedFalse();
        return category.stream().map(element -> new CategoryDto(
                        element.getId(),
                        element.getCategoryName(),
                        element.getCategoryDescription(),
                        element.getCreated(),
                        new StoreDto(
                                element.getStore().getId(),
                                element.getStore().getName(),
                                element.getStore().getAddress(),
                                element.getStore().getDescription(),
                                element.getStore().getCreated()
                        )
                )).toList();
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
            throw new ConflictException("Category with name " +categoryDto.getCategoryName()+" already exist");
        }
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCreated(categoryDto.getCreated());
        Store store = new Store();
        store.setId(categoryDto.getStore().getId());
        store.setName(categoryDto.getStore().getName());
        store.setDescription(categoryDto.getStore().getDescription());
        store.setAddress(categoryDto.getStore().getAddress());
        store.setCreated(categoryDto.getStore().getCreated());
        category.setStore(store);
        Category createCategory = this.categoryRepository.save(category);
        StoreDto storeDto = new StoreDto(
                createCategory.getStore().getId(),
                createCategory.getStore().getName(),
                createCategory.getStore().getAddress(),
                createCategory.getStore().getDescription(),
                createCategory.getStore().getCreated()
        );
        return new CategoryDto(
                createCategory.getId(),
                createCategory.getCategoryName(),
                createCategory.getCategoryDescription(),
                createCategory.getCreated(),
                storeDto
        );

    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return null;
    }

    @Override
    public CategoryDto updateCategory(Long id) {
        return null;
    }

    @Override
    public CategoryDto delete(Long id) {
        return null;
    }
}
