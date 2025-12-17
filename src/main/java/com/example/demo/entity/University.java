package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Unique;
@Entity
public class University{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @column(unique=true)
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
    public University(Long id,String name,String accreditationLevel,String country,Boolean active){
        this.id=id;
        this.name=name;
        this.accreditationLevel=accreditationLevel;
        this.country=country;
        this.active=active;
    }
    public University(){

    }

    
}