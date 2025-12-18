package.com.example.demo.service;
import com.example.demo.entity.CourseContentTopic;
public interface CourseContentTopic{
    createTopic(CourseContentTopic topic);
    getTopicsForCourse(Long courseId);
    getTopicById(Long id);
    updateTopic(Long id,CourseContentTopic topic);
}