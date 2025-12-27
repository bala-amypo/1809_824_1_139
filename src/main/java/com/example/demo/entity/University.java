// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "universities")
// public class University {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String name;

//     private boolean active = true;

//     public University() {
//     }

//     public University(Long id, String name, boolean active) {
//         this.id = id;
//         this.name = name;
//         this.active = active;
//     }

//     // getters & setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public boolean isActive() {
//         return active;
//     }

//     public void setActive(boolean active) {
//         this.active = active;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "University name cannot be empty")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(unique = true)
    private String name;

    // Constructors
    public University() {}
    public University(String name) { this.name = name; }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
