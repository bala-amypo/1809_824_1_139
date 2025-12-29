// // package com.example.demo.entity;

// // import jakarta.persistence.*;

// // @Entity
// // public class University {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     private String name;
// //     private boolean active = true;

// //     public Long getId() { return id; }
// //     public void setId(Long id) { this.id = id; }

// //     public String getName() { return name; }
// //     public void setName(String name) { this.name = name; }

// //     public boolean isActive() { return active; }
// //     public void setActive(boolean active) { this.active = active; }
// // }


// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "universities", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
// public class University {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String name;

//     private String accreditationLevel;
//     private String country;
//     private Boolean active = true;

//     public University() {}

//     public University(String name, String accreditationLevel, String country) {
//         this.name = name;
//         this.accreditationLevel = accreditationLevel;
//         this.country = country;
//         this.active = true;
//     }

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

//     public String getAccreditationLevel() {
//         return accreditationLevel;
//     }

//     public void setAccreditationLevel(String accreditationLevel) {
//         this.accreditationLevel = accreditationLevel;
//     }

//     public String getCountry() {
//         return country;
//     }

//     public void setCountry(String country) {
//         this.country = country;
//     }

//     public Boolean getActive() {
//         return active;
//     }

//     public void setActive(Boolean active) {
//         this.active = active;
//     }

//     public boolean isActive() {
//         return active != null && active;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String accreditationLevel;
    private String country;
    private boolean active;

    public University() {}

    public University(Long id, String name, String accreditationLevel,
                      String country, boolean active) {
        this.id = id;
        this.name = name;
        this.accreditationLevel = accreditationLevel;
        this.country = country;
        this.active = active;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccreditationLevel() { return accreditationLevel; }
    public void setAccreditationLevel(String accreditationLevel) {
        this.accreditationLevel = accreditationLevel;
    }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
