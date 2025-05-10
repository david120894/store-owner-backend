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
    public List<StoreDto> getAllStore() {
        List<Store> stores = this.storeRepository.findByDeletedFalse();
        return stores.stream().map(element -> new StoreDto(
                element.getId(),
                element.getName(),
                element.getAddress(),
                element.getDescription(),
                element.getCreated()
        )).toList();
    }

    @Override
    public StoreDto create(StoreDto store) {
        if (this.storeRepository.existsByName(store.getName())){
            throw new ConflictException("Store with name " + store.getName() + " already exists");
        }
        Store store1 = new Store();
        store1.setId(store.getId());
        store1.setName(store.getName());
        store1.setDescription(store.getDescription());
        store1.setAddress(store.getAddress());
        Store response = this.storeRepository.save(store1);
        return new StoreDto(
                response.getId(),
                response.getName(),
                response.getAddress(),
                response.getDescription(),
                response.getCreated());
    }

    @Override
    public StoreDto findById(Long id) {
        Store store = this.storeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Store not Found")
        );
        return new StoreDto(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getDescription(),
                store.getCreated()
        );
    }

    @Override
    public StoreDto update(Long id,StoreDto store) {
        if (this.storeRepository.existsByName(store.getName())) {
            throw  new ConflictException("Store already exist");
        }
        Store store1 = this.storeRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Store with " +id+ "not found"));
        store1.setName(store.getName());
        store1.setDescription(store.getDescription());
        store1.setAddress(store.getAddress());
        Store store2 = this.storeRepository.save(store1);

        return new StoreDto(
                store2.getId(),
                store2.getName(),
                store2.getAddress(),
                store2.getDescription(),
                store2.getCreated()
        );
    }

    @Override
    public StoreDto delete(Long id) {
        Store store =  this.storeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Store not found")
        );
        store.setDeleted(true);
        Store deletedStore = this.storeRepository.save(store);
        return new StoreDto(
                deletedStore.getId(),
                deletedStore.getName(),
                deletedStore.getAddress(),
                deletedStore.getDescription(),
                deletedStore.getCreated()
        );
    }
}
