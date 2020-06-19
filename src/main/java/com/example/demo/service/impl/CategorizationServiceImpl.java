package com.example.demo.service.impl;

import com.example.demo.exceptions.CategorizationNotFoundException;
import com.example.demo.model.Categorization;
import com.example.demo.repository.CategorizationRepository;
import com.example.demo.service.ServiceCategorization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationServiceImpl implements ServiceCategorization {

    private final CategorizationRepository categorizationRepository;

    public CategorizationServiceImpl(CategorizationRepository categorizationRepository) {
        this.categorizationRepository = categorizationRepository;
    }


    @Override
    public List<Categorization> findAll() {
        return this.categorizationRepository.findAll();
    }

    @Override
    public Categorization findById(Long id) {
        return this.categorizationRepository.findById(id). orElseThrow(()-> new CategorizationNotFoundException(id));
    }

    @Override
    public Categorization save(Categorization categorization) {
        return this.categorizationRepository.save(categorization);
    }

    @Override
    public Categorization update(Long id, Categorization categorization) {
        Categorization updatedCategorization = this.findById(id);
        updatedCategorization.setCategorizationName(categorization.getCategorizationName());
        updatedCategorization.setCategorization(categorization.getCategorization());
        return this.categorizationRepository.save(updatedCategorization);
    }

    @Override
    public void deleteById(Long id) {
        Categorization categorization = this.findById(id);
        this.categorizationRepository.removeById(id);
    }
}

