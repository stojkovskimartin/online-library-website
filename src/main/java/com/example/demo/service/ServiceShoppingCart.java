package com.example.demo.service;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.request.ChargeRequest;

import java.util.List;

public interface ServiceShoppingCart {

    ShoppingCart findActiveShoppingCartByFirstName(String firstName);

    List<ShoppingCart> findAllByFirstName(String firstName);

    ShoppingCart createNewShoppingCart(String firstName);

    ShoppingCart addBookToShoppingCart(String firstName,
                                       Long id);

    ShoppingCart removeBookFromShoppingCart(String firstName,
                                            Long id);

    ShoppingCart getActiveShoppingCart(String firstName);

    ShoppingCart cancelActiveShoppingCart(String firstName);

    ShoppingCart checkoutShoppingCart(String firstName, ChargeRequest chargeRequest);

}