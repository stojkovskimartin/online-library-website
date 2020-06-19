package com.example.demo.control.rest;

import com.example.demo.model.Library;
import com.example.demo.service.ServiceLibrary;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryRestController {

    private final ServiceLibrary serviceLibrary;

    public LibraryRestController(ServiceLibrary serviceLibrary) {
        this.serviceLibrary = serviceLibrary;
    }


    @GetMapping
    public List<Library> findAll() {
        return this.serviceLibrary.findAll();
    }

    @GetMapping("/{id}")
    public Library findById(@PathVariable Long id) {
        return this.serviceLibrary.findById(id);
    }

    @GetMapping("/by-category/{id}")
    public List<Library> findAllByCategoryId(@PathVariable Long id) {
        return this.serviceLibrary.findByCategorizationId(id);
    }


    @PostMapping
    public Library save(@Valid Library library, @RequestParam(required = false) MultipartFile image) throws IOException {
        return this.serviceLibrary.save(library, image);
    }


    @PutMapping("/{id}")
    public Library update(@PathVariable Long id,
                       @Valid Library library,
                       @RequestParam(required = false) MultipartFile image) throws IOException {
        return this.serviceLibrary.update(id, library, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.serviceLibrary.deleteById(id);
    }

}
