package com.example.backend_store.product.product.service.impl;

import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.auth.exceptions.NotFoundException;
import com.example.backend_store.product.category.dto.CategoryDto;
import com.example.backend_store.product.category.entity.Category;
import com.example.backend_store.product.category.repository.CategoryRepository;
import com.example.backend_store.product.product.dto.ProductDto;
import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.product.product.repository.ProductRepository;
import com.example.backend_store.product.product.service.ProductService;
import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.entity.Store;
import com.example.backend_store.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto create(ProductDto productDto) {
        if (this.productRepository.existsByName(productDto.getName())){
            throw new ConflictException("Product with name" +productDto.getName()+ "already exist");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(productDto.getCode());
        product.setCreated(productDto.getCreated());
        Category category = this.categoryRepository.findById(productDto.getCategoryDto().getId()).orElseThrow(
                () -> new NotFoundException("Category not found")
        );
        Store store = this.storeRepository.findById(category.getStore().getId()).orElseThrow(
                () -> new NotFoundException("Store not found")
        );
        category.setStore(store);
        product.setProductCategory(category);
        Product response =  this.productRepository.save(product);
        return new ProductDto(
                response.getId(),
                response.getName(),
                response.getCode(),
                response.getCreated(),
                new CategoryDto(
                        response.getProductCategory().getId(),
                        response.getProductCategory().getCategoryName(),
                        response.getProductCategory().getCategoryDescription(),
                        response.getProductCategory().getCreated(),
                        new StoreDto(
                                response.getProductCategory().getStore().getId(),
                                response.getProductCategory().getStore().getName(),
                                response.getProductCategory().getStore().getAddress(),
                                response.getProductCategory().getStore().getDescription(),
                                response.getProductCategory().getStore().getCreated()
                        )

                )
        );

    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto delete(Long id) {
        return null;
    }
}
