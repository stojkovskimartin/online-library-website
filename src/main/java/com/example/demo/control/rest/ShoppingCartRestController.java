package com.example.demo.control.rest;

import com.example.demo.model.ShoppingCart;
import com.example.demo.service.ServiceAuth;
import com.example.demo.service.ServiceShoppingCart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartRestController {

    private final ServiceShoppingCart serviceShoppingCart;
    private final ServiceAuth serviceAuth;

    public ShoppingCartRestController(ServiceShoppingCart serviceShoppingCart, ServiceAuth serviceAuth) {
        this.serviceShoppingCart = serviceShoppingCart;
        this.serviceAuth = serviceAuth;
    }


    @GetMapping
    public List<ShoppingCart> findAllByFirstName(String firstName) {
        return this.serviceShoppingCart.findAllByFirstName(firstName);
    }

    @PostMapping
    public ShoppingCart createNewShoppingCart() {
        return this.serviceShoppingCart.createNewShoppingCart(this.serviceAuth.getCurrentUserId());
    }

    @PatchMapping("/{id}/books")
    public ShoppingCart addProductToCart(@PathVariable Long id) {
        return this.serviceShoppingCart.addBookToShoppingCart(
                this.serviceAuth.getCurrentUserId(),
                id
        );
    }

    @DeleteMapping("/{id}/books")
    public ShoppingCart removeBookFromCart(@PathVariable Long id) {
        return this.serviceShoppingCart.removeBookFromShoppingCart(
                this.serviceAuth.getCurrentUserId(),
                id
        );
    }

    @PatchMapping("/cancel")
    public ShoppingCart cancelActiveShoppingCart() {
        return this.serviceShoppingCart.cancelActiveShoppingCart(this.serviceAuth.getCurrentUserId());
    }

    @PatchMapping("/checkout")
    public ShoppingCart checkoutActiveShoppingCart() {
//        return this.shoppingCartService.checkoutShoppingCart(this.authService.getCurrentUserId());
        return null;
    }



}