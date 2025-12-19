@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final UniversityRepository univRepo;

    public CourseServiceImpl(CourseRepository repo, UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    public Course createCourse(Course c) {
        if (c.getCreditHours() <= 0)
            throw new IllegalArgumentException("> 0");
        return repo.save(c);
    }
}
