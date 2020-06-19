package com.example.demo.control.rest;

import com.example.demo.model.Categorization;
import com.example.demo.service.ServiceCategorization;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categorization")
public class CategorizationRestController {


    private final ServiceCategorization serviceCategorization;

    public CategorizationRestController(ServiceCategorization serviceCategorization) {
        this.serviceCategorization = serviceCategorization;
    }


    @GetMapping
    public List<Categorization> findAll() {
        return this.serviceCategorization.findAll();
    }


    @GetMapping("/{id}")
    public Categorization findById(@PathVariable Long id) {
        return this.serviceCategorization.findById(id);
    }

    @PostMapping
    public Categorization save(@Valid Categorization categorization) {
        return this.serviceCategorization.save(categorization);
    }

    @PutMapping("/{id}")
    public Categorization update(@PathVariable Long id, @Valid Categorization categorization) {
        return this.serviceCategorization.update(id, categorization);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.serviceCategorization.deleteById(id);
    }
}

