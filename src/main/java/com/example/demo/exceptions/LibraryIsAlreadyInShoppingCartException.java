package com.example.demo.exceptions;

public class LibraryIsAlreadyInShoppingCartException extends  RuntimeException{
    public LibraryIsAlreadyInShoppingCartException(String name) {
        super(String.format("Product %s is already in active shopping cart", name));
    }
}
