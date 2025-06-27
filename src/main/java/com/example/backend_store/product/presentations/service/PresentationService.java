package com.example.backend_store.product.presentations.service;

import com.example.backend_store.product.presentations.dto.PresentationDto;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationService {
    PresentationDto create (PresentationDto presentationDto);
    PresentationDto update(Long id,PresentationDto presentationDto);
    String uploadImg(Long id, MultipartFile img);

}
