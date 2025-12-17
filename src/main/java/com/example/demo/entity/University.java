package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
@Entity
public class University{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    private String accreditationLevel;
    private String country;
    private Boolean active;

    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }
    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
     public String getaccreditationLevel(){
        return accreditationLevel;
    }
    public void setaccreditationLevel(String accreditation){
        this.accreditation=accreditation;
    }
     public String getcountry(){
        return country;
    }
    public void setcountry(String country){
        this.country=country;
    }
     public Boolean getactive(){
        return active;
    }
    public void setactive(String active){
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