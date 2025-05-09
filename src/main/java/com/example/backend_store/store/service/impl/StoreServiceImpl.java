package com.example.backend_store.store.service.impl;

import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.auth.exceptions.NotFoundException;
import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.entity.Store;
import com.example.backend_store.store.repository.StoreRepository;
import com.example.backend_store.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<StoreDto> findAll() {
        return List.of();
    }

    @Override
    public StoreDto create(StoreDto store) {
        if (this.storeRepository.existsByName(store.getName())){
            throw new ConflictException("Store with name " + store.getName() + " already exists");
        }
        return null;
    }

    @Override
    public StoreDto findById(Long id) {
        return null;
    }

    @Override
    public StoreDto update(StoreDto store) {
        return null;
    }

    @Override
    public void delete(StoreDto store) {

    }
}
