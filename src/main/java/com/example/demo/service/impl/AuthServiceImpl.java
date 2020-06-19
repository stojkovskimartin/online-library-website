package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ServiceAuth;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements ServiceAuth {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        return this.userRepository.findById(10l)
                .orElseGet(() -> {
                    User user = new User();
                    user.setFirstName("emt-user");
                    return this.userRepository.save(user);
                });
    }
    @Override
    public String getCurrentUserId() {
        return this.getCurrentUser().getFirstName();
    }
}
