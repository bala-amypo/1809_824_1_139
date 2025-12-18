package.com.example.demo.service;
import com.example.demo.entity.University;
import java.util.List;
public interface UniversityService{
    createUniversity(University univ);
    updateUniversity(Long id,University univ);
    getUniversityById(Long id);
    List<University>getAllUniversities();
    deactivateUniversity(Long id);
}