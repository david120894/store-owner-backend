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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = this.productRepository.findByDeletedFalse();
        return products.stream().map(element -> new ProductDto(
                        element.getId(),
                        element.getName(),
                        element.getCode(),
                        element.getDescription(),
                        element.getImageUrl(),
                        element.getCreated(),
                        new CategoryDto(
                                element.getProductCategory().getId(),
                                element.getProductCategory().getCategoryName(),
                                element.getProductCategory().getCategoryDescription(),
                                element.getProductCategory().getCreated(),
                                new StoreDto(
                                        element.getProductCategory().getStore().getId(),
                                        element.getProductCategory().getStore().getName(),
                                        element.getProductCategory().getStore().getAddress(),
                                        element.getProductCategory().getStore().getDescription(),
                                        element.getProductCategory().getStore().getPhone(),
                                        element.getProductCategory().getStore().getCreated()
                                )
                        )
                )).toList();
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        if (this.productRepository.existsByName(productDto.getName())){
            throw new ConflictException("Product with name " +productDto.getName()+ " already exist");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(productDto.getCode());
        product.setCreated(productDto.getCreated());
        product.setImageUrl("/images/default-product.png");
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
                response.getDescription(),
                response.getImageUrl(),
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
                                response.getProductCategory().getStore().getPhone(),
                                response.getProductCategory().getStore().getCreated()
                        )

                )
        );

    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product product =  this.productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product with name " +productDto.getName()+ "no found")
        );
        product.setName(productDto.getName());
        product.setCode(productDto.getCode());
        Category category = this.categoryRepository.findById(productDto.getCategoryDto().getId()).orElseThrow(
                ()-> new NotFoundException("Category no found")
        );
        product.setProductCategory(category);
        Product product1 = this.productRepository.save(product);

        Category category1 = this.categoryRepository.findById(product1.getProductCategory().getId()).orElseThrow(
                () -> new NotFoundException("Category no found")
        );
        Store store = this.storeRepository.findById(category1.getStore().getId()).orElseThrow(
                ()-> new NotFoundException("Store no found")
        );

        return new ProductDto(
                product1.getId(),
                product1.getName(),
                product1.getCode(),
                product1.getDescription(),
                product1.getImageUrl(),
                product1.getCreated(),
                new CategoryDto(
                        category1.getId(),
                        category1.getCategoryName(),
                        category1.getCategoryDescription(),
                        category1.getCreated(),
                        new StoreDto(
                                store.getId(),
                                store.getName(),
                                store.getAddress(),
                                store.getDescription(),
                                store.getPhone(),
                                store.getCreated()
                        )
                )

        );

    }

    @Override
    public String uploadsImg(Long id, MultipartFile name) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + name.getOriginalFilename();
        Path imagePath = Paths.get("uploads/" + fileName);
        Files.copy(name.getInputStream(), imagePath);

        String imageUrl = "/images/" + fileName;
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        product.setImageUrl(imageUrl);
        Product product1 = this.productRepository.save(product);
        return product1.getImageUrl();
    }

    @Override
    public ProductDto delete(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        product.setDeleted(true);
        Product deletedProduct = this.productRepository.save(product);
        Category category = deletedProduct.getProductCategory();
        Store store = category.getStore();
        return new ProductDto(
                deletedProduct.getId(),
                deletedProduct.getName(),
                deletedProduct.getCode(),
                deletedProduct.getDescription(),
                deletedProduct.getImageUrl(),
                deletedProduct.getCreated(),
                new CategoryDto(
                        category.getId(),
                        category.getCategoryName(),
                        category.getCategoryDescription(),
                        category.getCreated(),
                        new StoreDto(
                                store.getId(),
                                store.getName(),
                                store.getAddress(),
                                store.getDescription(),
                                store.getPhone(),
                                store.getCreated()
                        )
                )
        );
    }
}
