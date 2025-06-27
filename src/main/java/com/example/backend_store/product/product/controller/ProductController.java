package com.example.backend_store.product.product.controller;


import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.product.product.dto.ProductDto;
import com.example.backend_store.product.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProduct() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "List product successfully",
                        HttpStatus.OK.value(),
                        productService.getAllProduct()
                )
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> create(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Create product successfully",
                        HttpStatus.OK.value(),
                        productService.create(productDto)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Updated product successfully",
                        HttpStatus.OK.value(),
                        this.productService.update(id,productDto)
                )
        );
    }

    @PostMapping("/upload/img/{id}")
    public ResponseEntity<ApiResponse<String>> uploads(@PathVariable Long id, @RequestParam("file")MultipartFile image) throws IOException {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Image created successfully",
                        HttpStatus.OK.value(),
                        this.productService.uploadsImg(id,image)
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Deleted product successfully",
                        HttpStatus.OK.value(),
                        this.productService.delete(id)
                )
        );
    }
}
