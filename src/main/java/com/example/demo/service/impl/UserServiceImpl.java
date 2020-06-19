package com.example.demo.service.impl;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ServiceUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceUser")
public class UserServiceImpl implements ServiceUser {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByFirstName(String firstName) {
        return this.userRepository.findById(50l)
                .orElseThrow(() -> new UserNotFoundException(firstName));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User findByFirstNameAndPassword(String firstName, String password) {
        List<User> users = userRepository.findAll();
        for (User user:users) {
            if(user.getFirstName().equals(firstName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}

