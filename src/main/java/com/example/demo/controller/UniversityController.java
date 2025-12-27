// package com.example.demo.controller;

// import com.example.demo.entity.University;
// import com.example.demo.service.impl.UniversityServiceImpl;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/universities")
// public class UniversityController {

//     private final UniversityServiceImpl service = new UniversityServiceImpl();

//     @PostMapping
//     public University create(@RequestBody University u) {
//         return service.createUniversity(u);
//     }

//     @PutMapping("/{id}")
//     public University update(@PathVariable Long id, @RequestBody University u) {
//         return service.updateUniversity(id, u);
//     }

//     @GetMapping("/{id}")
//     public University get(@PathVariable Long id) {
//         return service.getUniversityById(id);
//     }

//     @DeleteMapping("/{id}")
//     public void deactivate(@PathVariable Long id) {
//         service.deactivateUniversity(id);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@Tag(name = "University")
public class UniversityController {

    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<University> create(@RequestBody University u) {
        return ResponseEntity.ok(service.createUniversity(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, @RequestBody University u) {
        return ResponseEntity.ok(service.updateUniversity(id, u));
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUniversityById(id));
    }

    @GetMapping
    public ResponseEntity<List<University>> getAll() {
        return ResponseEntity.ok(service.getAllUniversities());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateUniversity(id);
        return ResponseEntity.noContent().build();
    }
}