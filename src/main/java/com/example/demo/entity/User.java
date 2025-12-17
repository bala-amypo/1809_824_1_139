package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import  java.time.LocalDateTime;
@Entity
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String email;
    private String password;
    private String roles;
    private LocalDateTime createdAt;

     public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }
     public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email=email;
    }
     public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password=password;
    }
     public String getroles(){
        return roles;
    }
    public void setroles(String roles){
        this.roles=roles;
    }
     public LocalDateTime getcreatedAt(){
        return createdAt;
    }
    public void setcreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public User(Long id,String email,String password,String roles,LocalDateTime createdAt){
        this.id=id;
        this.email=email;
        this.password=password;
        this.roles=roles;
        this.createdAt=createdAt;
    }
    public User(){
        
    }


    
}