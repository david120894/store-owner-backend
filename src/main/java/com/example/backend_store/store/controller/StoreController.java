package com.example.backend_store.store.controller;

import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.store.dto.StoreDto;
import com.example.backend_store.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<StoreDto>>> getStore() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Store successfully",
                        HttpStatus.OK.value(),
                        storeService.getAllStore()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreDto>> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Store successfully",
                        HttpStatus.OK.value(),
                        storeService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StoreDto>> createStore(@RequestBody StoreDto storeDto){
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Create susccessfully",
                        HttpStatus.OK.value(),
                        storeService.create(storeDto)
                )
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreDto>> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Update successfully",
                        HttpStatus.OK.value(),
                        storeService.update(id,storeDto)
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreDto>> deleteStore(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Deleted successfully",
                        HttpStatus.OK.value(),
                        storeService.delete(id)
                )
        );
    }
}
