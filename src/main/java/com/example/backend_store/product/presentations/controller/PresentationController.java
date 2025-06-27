package com.example.backend_store.product.presentations.controller;

import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.product.presentations.dto.PresentationDto;
import com.example.backend_store.product.presentations.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/presentation")
public class PresentationController {

    @Autowired
    private PresentationService presentationService;

    @PostMapping
    public ResponseEntity<ApiResponse<PresentationDto>> create(@RequestBody PresentationDto presentationDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Created presentation successfully",
                        HttpStatus.OK.value(),
                        presentationService.create(presentationDto)
                )
        );
    }
}
