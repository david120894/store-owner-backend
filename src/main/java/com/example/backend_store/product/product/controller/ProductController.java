package com.example.backend_store.product.product.controller;


import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.product.product.dto.ProductDto;
import com.example.backend_store.product.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

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
}
