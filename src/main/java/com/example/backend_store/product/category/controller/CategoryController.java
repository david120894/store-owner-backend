package com.example.backend_store.product.category.controller;

import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.product.category.dto.CategoryDto;
import com.example.backend_store.product.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "List Category successfully",
                        HttpStatus.OK.value(),
                        categoryService.getAllCategory()
                )
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "created category successfully",
                        HttpStatus.OK.value(),
                        categoryService.createCategory(categoryDto)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable Long id,@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Update category successfully",
                        HttpStatus.OK.value(),
                        categoryService.updateCategory(id,categoryDto)

                )
        );
    }
}
