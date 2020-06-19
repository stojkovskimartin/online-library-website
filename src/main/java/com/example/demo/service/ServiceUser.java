package com.example.demo.service;

import com.example.demo.model.User;

public interface ServiceUser {
        User findByFirstName(String firstName);
        User findByEmail(String email);
        User findByFirstNameAndPassword(String firstName, String password);
        void saveUser(User user);
    }
