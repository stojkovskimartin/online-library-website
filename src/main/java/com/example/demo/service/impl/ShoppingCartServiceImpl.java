package com.example.demo.service.impl;

import com.example.demo.enumerations.CartStatus;
import com.example.demo.exceptions.LibraryIsAlreadyInShoppingCartException;
import com.example.demo.exceptions.LibraryOutOfStockException;
import com.example.demo.exceptions.ShoppingCartIsAlreadyCreated;
import com.example.demo.exceptions.ShoppingCartIsNotActiveException;
import com.example.demo.model.Library;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.request.ChargeRequest;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.service.ServiceLibrary;
import com.example.demo.service.ServiceShoppingCart;
import com.example.demo.service.ServiceUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ServiceShoppingCart {

    private final ServiceUser serviceUser;
    private final ServiceLibrary serviceLibrary;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ServiceUser serviceUser, ServiceLibrary serviceLibrary, ShoppingCartRepository shoppingCartRepository) {
        this.serviceUser = serviceUser;
        this.serviceLibrary = serviceLibrary;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public ShoppingCart findActiveShoppingCartByFirstName(String firstName) {
        return this.shoppingCartRepository.findByUserFirstNameAndStatus(firstName, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(firstName));
    }

    @Override
    public List<ShoppingCart> findAllByFirstName(String firstName) {
        return this.shoppingCartRepository.findAllByUserFirstName(firstName);
    }

    @Override
    public ShoppingCart createNewShoppingCart(String firstName) {
        User user = this.serviceUser.findByFirstName(firstName);
        if (this.shoppingCartRepository.existsByUserFirstNameAndStatus(
                user.getFirstName(),
                CartStatus.CREATED
        )) {
            throw new ShoppingCartIsAlreadyCreated(firstName);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart addBookToShoppingCart(String firstName, Long id) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(firstName);
        Library library = this.serviceLibrary.findById(id);
        for (Library library1 : shoppingCart.getLibraries()) {
            if (library1.getId().equals(id)) {
                throw new LibraryIsAlreadyInShoppingCartException(library.getBook());
            }
        }
        shoppingCart.getLibraries().add(library);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeBookFromShoppingCart(String firstName, Long id) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(firstName);
        shoppingCart.setLibraries(
                shoppingCart.getLibraries()
                        .stream()
                        .filter(book -> !book.getId().equals(id))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String firstName) {
        return this.shoppingCartRepository
                .findByUserFirstNameAndStatus(firstName, CartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    User user = this.serviceUser.findByFirstName(firstName);
                    shoppingCart.setUser(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart cancelActiveShoppingCart(String firstName) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserFirstNameAndStatus(firstName, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(firstName));
        shoppingCart.setStatus(CartStatus.CANCELED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(String firstName, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserFirstNameAndStatus(firstName, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(firstName));

        List<Library> libraries = shoppingCart.getLibraries();
        float price = 0;

        for (Library library : libraries) {
            if (library.getNum() <= 0) {
                throw new LibraryOutOfStockException(library.getBook());
            }
            library.setNum(library.getNum() - 1);
            price += library.getPrice();
        }

        shoppingCart.setLibraries(libraries);
        shoppingCart.setStatus(CartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
