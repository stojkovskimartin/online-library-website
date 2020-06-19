package com.example.demo.service;

import com.example.demo.model.User;

public interface ServiceAuth {
    User getCurrentUser();
    String getCurrentUserId();
}
