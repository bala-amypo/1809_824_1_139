import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService atrs;

    @PostMapping("/")
    public University addUniversity(@Valid @RequestBody University university) {
        return atrs.createUniversity(university);
    }

    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,
                                       @Valid @RequestBody University university) {
        return atrs.updateUniversity(id, university);
    }

    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Long id) {
        return atrs.getViewById(id);
    }

    @GetMapping("/")
    public List<University> getUniversities() {
        return atrs.getAllUniversities();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateUniversity(@PathVariable Long id) {
        atrs.deactivateUniversity(id);
    }
}