package com.example.backend_store.store.service;

import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    List<StoreDto> getAllStore();
    StoreDto create(StoreDto store);
    StoreDto findById(Long id);
    StoreDto update(Long id,StoreDto store);
    StoreDto delete(Long id);
}
