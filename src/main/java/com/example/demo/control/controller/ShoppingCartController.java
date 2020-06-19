package com.example.demo.control.controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.service.ServiceAuth;
import com.example.demo.service.ServiceShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ServiceShoppingCart serviceShoppingCart;
    private final ServiceAuth serviceAuth;

    public ShoppingCartController(ServiceShoppingCart serviceShoppingCart, ServiceAuth serviceAuth) {
        this.serviceShoppingCart = serviceShoppingCart;
        this.serviceAuth = serviceAuth;
    }


    @GetMapping
    public String test(){
        return "cart";
    }

    @PostMapping("/{id}/add-book")
    public String addBookToShoppingCart(@PathVariable Long id) {
        try {
            ShoppingCart shoppingCart = this.serviceShoppingCart.addBookToShoppingCart(this.serviceAuth.getCurrentUserId(), id);
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/books";
    }


    @PostMapping("/{id}/remove-book")
    public String removeBookFromShoppingCart(@PathVariable Long id) {
        ShoppingCart shoppingCart = this.serviceShoppingCart.removeBookFromShoppingCart(this.serviceAuth.getCurrentUserId(), id);
        return "redirect:/books";
    }
}
