package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String accreditationLevel;
    private String country;
    private Boolean active;

    

    public University(Long id,String name, String accreditationLevel, String country, Boolean active) {
        this.name = name;
        this.id=id;
        this.accreditationLevel = accreditationLevel;
        this.country = country;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccreditationLevel() {
        return accreditationLevel;
    }

    public void setAccreditationLevel(String accreditationLevel) {
        this.accreditationLevel = accreditationLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    public University() {
    }

    
}