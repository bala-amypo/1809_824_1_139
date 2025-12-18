package.com.example.demo.service;
import com.example.demo.entity.University;
i p
public interface UniversityService{
    createUniversity(University univ);
    updateUniversity(Long id,University univ);
    getUniversityById(Long id);
    List<University>getAllUniversities();
}