package com.example.demo.control.controller;

import com.example.demo.model.Categorization;
import com.example.demo.model.Library;
import com.example.demo.service.ServiceCategorization;
import com.example.demo.service.ServiceLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class ControlLibrary {

    @Autowired
    private final ServiceLibrary serviceLibrary;
    @Autowired
    private final ServiceCategorization serviceCategorization;

    public ControlLibrary(ServiceLibrary serviceLibrary, ServiceCategorization serviceCategorization) {
        this.serviceLibrary = serviceLibrary;
        this.serviceCategorization = serviceCategorization;
    }


    @GetMapping
    public String listBooks(Model model)
    {
        List<Library> library = this.serviceLibrary.findAll();
        model.addAttribute("library", library);
        return "products";
    }

    @PostMapping
    public String saveOrUpdateBook(Model model, @Valid Library library, BindingResult bindingResult,  MultipartFile image) throws IOException {
        if(bindingResult.hasErrors())
        {
            List<Categorization> categorization = this.serviceCategorization.findAll();
            model.addAttribute("categorization", categorization);
            return "add";
        }

        if(library.getId() == null)
        {
            Library newLibrary =  this.serviceLibrary.save(library, image);
        } else {
            this.serviceLibrary.update(library.getId(), library, image);
        }
        return "redirect:/books";
    }

    @GetMapping("/add-new")
    public String addNewBook(Model model)
    {
        List<Categorization> categorization = this.serviceCategorization.findAll();
        model.addAttribute("library", new Library());
        model.addAttribute("categorization", categorization);
        return "add";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable Long id, Model model)
    {
        try {
            Library library = this.serviceLibrary.findById(id);
            List<Categorization> categorization = this.serviceCategorization.findAll();
            model.addAttribute("library", library);
            model.addAttribute("categorization", categorization);
            return "add";
        } catch (RuntimeException ex)
        {
            return "redirect:/books?error=" + ex.getLocalizedMessage();
        }
    }
    @DeleteMapping("/{id}/delete")
    public String deleteBookWithDelete(@PathVariable Long id){
        this.serviceLibrary.deleteById(id);
        return "redirect:/books";
    }
    @PostMapping (value = "/{id}/delete")
    public String deleteBookWithPost(@PathVariable Long id){
        serviceLibrary.deleteById(id);
        return "redirect:/books";
    }
}
