package com.example.demo.repository;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    Optional<University> findByName(String name);
    List<University> findByCountry(String country);
}
