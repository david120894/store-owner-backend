package com.example.backend_store.product.product.service;

import com.example.backend_store.product.product.dto.ProductDto;

public interface ProductService {
    ProductDto create(ProductDto productDto);
    ProductDto update(Long id, ProductDto productDto);
    ProductDto delete(Long id);


}
