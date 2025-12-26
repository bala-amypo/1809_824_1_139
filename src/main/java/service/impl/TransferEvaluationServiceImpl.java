// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.service.TransferEvaluationService;
// import com.example.demo.entity.TransferEvaluationResult;
// import com.example.demo.entity.Course;
// import com.example.demo.repository.TransferEvaluationResultRepository;
// import com.example.demo.repository.CourseRepository;

// import java.util.List;

// @Service
// public class TransferEvaluationServiceImpl implements TransferEvaluationService {

//     private final TransferEvaluationResultRepository resultRepo;
//     private final CourseRepository courseRepo;

//     public TransferEvaluationServiceImpl(TransferEvaluationResultRepository resultRepo,
//                                          CourseRepository courseRepo) {
//         this.resultRepo = resultRepo;
//         this.courseRepo = courseRepo;
//     }

//     @Override
//     public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
//         Course sourceCourse = courseRepo.findById(sourceCourseId)
 //                 .orElseThrow(() -> new RuntimeException("Source course not found"));
//         Course targetCourse = courseRepo.findById(targetCourseId)
//                 .orElseThrow(() -> new RuntimeException("Target course not found"));

//         TransferEvaluationResult result = new TransferEvaluationResult();
//         result.setSourceCourse(sourceCourse);
//         result.setTargetCourse(targetCourse);

//         if (!sourceCourse.getActive() || !targetCourse.getActive()) {
//             result.setIsEligibleForTransfer(false);
//             result.setNotes("Course not active");
//         } else {
           
//             double overlap = 100.0; 
//             int creditDiff = Math.abs(sourceCourse.getCreditHours() - targetCourse.getCreditHours());

//             result.setOverlapPercentage(overlap);
//             result.setCreditHourDifference(creditDiff);

//             if (overlap >= 50 && creditDiff <= 1) {
//                 result.setIsEligibleForTransfer(true);
//                 result.setNotes("Eligible for transfer");
//             } else {
//                 result.setIsEligibleForTransfer(false);
//                 result.setNotes("No active rule satisfied");
//             }
//         }

//         return resultRepo.save(result);
//     }

//     @Override
//     public TransferEvaluationResult getEvaluationById(Long id) {
//         return resultRepo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Transfer evaluation not found"));
//     }

//     @Override
//     public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
//         return resultRepo.findBySourceCourseId(courseId);
//     }
 // }
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class TransferEvaluationServiceImpl {

    CourseRepository courseRepo;
    CourseContentTopicRepository topicRepo;
    TransferRuleRepository ruleRepo;
    TransferEvaluationResultRepository resultRepo;

    public TransferEvaluationResult evaluateTransfer(Long srcId, Long tgtId) {

        Course src = courseRepo.findById(srcId)
                .orElseThrow(() -> new RuntimeException("not found"));
        Course tgt = courseRepo.findById(tgtId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!src.isActive() || !tgt.isActive())
            throw new IllegalArgumentException("active");

        List<CourseContentTopic> srcTopics = topicRepo.findByCourseId(srcId);
        List<CourseContentTopic> tgtTopics = topicRepo.findByCourseId(tgtId);

        double overlap = 0;
        for (CourseContentTopic s : srcTopics) {
            for (CourseContentTopic t : tgtTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    overlap += Math.min(
                            Optional.ofNullable(s.getWeightPercentage()).orElse(0.0),
                            Optional.ofNullable(t.getWeightPercentage()).orElse(0.0)
                    );
                }
            }
        }

        List<TransferRule> rules = ruleRepo
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        src.getUniversity().getId(),
                        tgt.getUniversity().getId());

        TransferEvaluationResult res = new TransferEvaluationResult();
        res.setOverlapPercentage(overlap);

        boolean eligible = false;
        for (TransferRule r : rules) {
            if (overlap >= r.getMinimumOverlapPercentage() &&
                    Math.abs(src.getCreditHours() - tgt.getCreditHours())
                            <= Optional.ofNullable(r.getCreditHourTolerance()).orElse(0)) {
                eligible = true;
            }
        }

        res.setIsEligibleForTransfer(eligible);
        res.setNotes(eligible ? "Eligible" :
                rules.isEmpty() ? "No active transfer rule" : "No active rule satisfied");

        return resultRepo.save(res);
    }

    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<TransferEvaluationResult> getEvaluationsForCourse(Long id) {
        return resultRepo.findBySourceCourseId(id);
    }
}
