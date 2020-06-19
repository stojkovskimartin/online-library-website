package com.example.demo.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categorization")
public class Categorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 1, message = "Name should not be empty")
    @Column(name = "name")
    private String categorizationName;

    @Column(name = "categorization")
    private String categorization;

    public Categorization(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorizationName() {
        return categorizationName;
    }

    public void setCategorizationName(String categorizationName) {
        this.categorizationName = categorizationName;
    }

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }
}
