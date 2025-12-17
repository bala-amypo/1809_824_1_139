package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class University{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String accreditationLevel;
    private String country;
    private Boolean active;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public string getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
     public string getAccreditationLevel(){
        return accreditation;
    }
    public void setAccreditationLevel(String accreditation){
        this.accreditation=accreditation;
    }
     public string getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country=country;
    }
     public Boolean getActive(){
        return active;
    }
    public void setActive(String active){
        this.active=active;
    }

    
}