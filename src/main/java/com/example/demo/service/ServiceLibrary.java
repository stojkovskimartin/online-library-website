package com.example.demo.service;

import com.example.demo.model.Library;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServiceLibrary {
    List<Library> findByCategorizationId(Long id);
    List<Library> findAll();
    Library findById(Long id);
    Library save(Library library, MultipartFile image) throws IOException;
    Library update(Long id, Library library, MultipartFile image) throws IOException;
    void deleteById(Long id);
}