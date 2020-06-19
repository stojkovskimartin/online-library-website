package com.example.demo.service.impl;

import com.example.demo.exceptions.LibraryNotFoundException;
import com.example.demo.model.Categorization;
import com.example.demo.model.Library;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.service.ServiceCategorization;
import com.example.demo.service.ServiceLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Transactional
@Service
public class LibraryServiceImpl implements ServiceLibrary {

    @Autowired
    private final LibraryRepository libraryRepository;
    @Autowired
    private final ServiceCategorization serviceCategorization;

    public LibraryServiceImpl(LibraryRepository libraryRepository, ServiceCategorization serviceCategorization) {
        this.libraryRepository = libraryRepository;
        this.serviceCategorization = serviceCategorization;
    }

    @Override
    public List<Library> findByCategorizationId(Long id) {
        return null;
    }

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Library findById(Long id) {
        return libraryRepository.findById(id) .orElseThrow(() -> new LibraryNotFoundException(id));
    }

    @Override
    public Library save(Library library, MultipartFile image) throws IOException {
        Categorization categorization = this.serviceCategorization.findById(library.getCatBook().getId());
        library.setCatBook(categorization);
        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            library.setImg(base64Image);
        }
        return this.libraryRepository.save(library);
    }

    @Override
    public Library update(Long id, Library library, MultipartFile image) throws IOException {
        Library updatedProduct = this.findById(id);
        Categorization categorization = this.serviceCategorization.findById(library.getCatBook().getId());
        updatedProduct.setCatBook(categorization);
        updatedProduct.setBook(library.getBook());
        updatedProduct.setNum(library.getNum());
        updatedProduct.setPrice(library.getPrice());

        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            updatedProduct.setImg(base64Image);
        }
        return this.libraryRepository.save(updatedProduct);
    }

    @Override
    public void deleteById(Long id) {
       libraryRepository.deleteById(id);
    }
}
