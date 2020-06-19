package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException(Long id) {
        super(String.format("Product with id %d is not found!", id));
    }
}
