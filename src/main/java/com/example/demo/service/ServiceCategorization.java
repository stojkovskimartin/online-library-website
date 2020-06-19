package com.example.demo.service;

import com.example.demo.model.Categorization;

import java.util.List;

public interface ServiceCategorization {
    List<Categorization> findAll();
    Categorization findById(Long id);
    Categorization save(Categorization categorization);
    Categorization update(Long id, Categorization categorization);
    void deleteById(Long id);
}
