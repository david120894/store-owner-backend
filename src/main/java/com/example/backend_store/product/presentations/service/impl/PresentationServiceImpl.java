package com.example.backend_store.product.presentations.service.impl;

import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.auth.exceptions.NotFoundException;
import com.example.backend_store.product.category.dto.CategoryDto;
import com.example.backend_store.product.category.entity.Category;
import com.example.backend_store.product.category.repository.CategoryRepository;
import com.example.backend_store.product.presentations.dto.PresentationDto;
import com.example.backend_store.product.presentations.entity.Presentation;
import com.example.backend_store.product.presentations.repository.PresentationRepository;
import com.example.backend_store.product.presentations.service.PresentationService;
import com.example.backend_store.product.product.dto.ProductDto;
import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.product.product.repository.ProductRepository;
import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.entity.Store;
import com.example.backend_store.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PresentationServiceImpl implements PresentationService {
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Override
    public PresentationDto create(PresentationDto presentationDto) {

        if (this.presentationRepository.existsByName(presentationDto.getName())) {
            throw new ConflictException("Presentation already exists");
        }
        Product product = this.productRepository.findById(presentationDto.getProduct().getId()).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        Presentation presentation = new Presentation();
        presentation.setDescription(presentationDto.getDescription());
        presentation.setImageUrl("default-product.png");
        presentation.setPrice(presentationDto.getPrice());
        presentation.setUnit(presentationDto.getUnit());
        presentation.setUnitType(presentationDto.getUnitType());
        presentation.setStockQuantity(presentationDto.getStockQuantity());
        presentation.setProduct(product);
        
        Presentation presentation1 =this.presentationRepository.save(presentation);
        Category category = product.getProductCategory();
        Store store = category.getStore();

        return new PresentationDto(
                presentation1.getId(),
                presentation1.getName(),
                presentation1.getDescription(),
                presentation1.getImageUrl(),
                presentation1.getPrice(),
                presentation1.getUnit(),
                presentation1.getUnitType(),
                presentation1.getStockQuantity(),
                new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getCode(),
                        product.getCreated(),
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
                                        store.getCreated()
                                )
                        )
                )

        );
    }

    @Override
    public PresentationDto update(Long id, PresentationDto presentationDto) {
        return null;
    }

    @Override
    public String uploadImg(Long id, MultipartFile img) {
        return "";
    }
}
