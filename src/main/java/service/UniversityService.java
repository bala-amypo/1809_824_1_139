// package com.example.demo.service;

// import com.example.demo.entity.University;

// public interface UniversityService {
//     University createUniversity(University university);
//     University updateUniversity(Long id, University university);
//     University getUniversityById(Long id);
//     void deactivateUniversity(Long id);
// }


// package com.example.demo.service;

// import com.example.demo.entity.University;
// import java.util.List;

// public interface UniversityService {

//     University createUniversity(University university);

//     University updateUniversity(Long id, University updated);

//     University getUniversityById(Long id);

//     void deactivateUniversity(Long id);

//     List<University> getAllUniversities();
// }

package com.example.demo.service;

import com.example.demo.entity.University;

public interface UniversityService {
    University saveUniversity(University university);
}
