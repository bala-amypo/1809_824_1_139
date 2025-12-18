import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService atrs;

    @PostMapping("/")
    public ResponseEntity<University> addUniversity(@Valid @RequestBody University university) {
        University created = atrs.createUniversity(university);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id,
                                                       @Valid @RequestBody University university) {
        University updated = atrs.updateUniversity(id, university);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        University university = atrs.getUniversityById(id);  // Fixed method name
        return ResponseEntity.ok(university);
    }

    @GetMapping("/")
    public ResponseEntity<List<University>> getUniversities() {
        List<University> list = atrs.getAllUniversities();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUniversity(@PathVariable Long id) {
        atrs.deactivateUniversity(id);
        return ResponseEntity.noContent().build();
    }
}