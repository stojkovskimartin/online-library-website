package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class LibraryOutOfStockException extends RuntimeException{
    public LibraryOutOfStockException(String name) {
        super(String.format("Product with id %d is out of stock!", name));
    }
}
