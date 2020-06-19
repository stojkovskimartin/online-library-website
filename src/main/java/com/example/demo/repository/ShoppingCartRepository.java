package com.example.demo.repository;

import com.example.demo.enumerations.CartStatus;
import com.example.demo.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserFirstNameAndStatus(String firstName, CartStatus status);
    boolean existsByUserFirstNameAndStatus(String firstName, CartStatus status);
    List<ShoppingCart> findAllByUserFirstName(String firstName);
}