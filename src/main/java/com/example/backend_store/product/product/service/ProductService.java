package com.example.backend_store.product.product.service;

import com.example.backend_store.product.product.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDto create(ProductDto productDto);
    ProductDto update(Long id, ProductDto productDto);
    String uploadsImg(Long id, MultipartFile name) throws IOException;
    ProductDto delete(Long id);


}
