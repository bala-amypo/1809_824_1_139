// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;

// import java.util.*;

//  public class TransferEvaluationServiceImpl {

//     private CourseRepository courseRepo;
//     private CourseContentTopicRepository topicRepo;
//     private TransferRuleRepository ruleRepo;
//     private TransferEvaluationResultRepository resultRepo;

//     public TransferEvaluationResult evaluateTransfer(Long srcId, Long tgtId) {

//         Course src = courseRepo.findById(srcId).orElseThrow();
//         Course tgt = courseRepo.findById(tgtId).orElseThrow();

//         if (!src.isActive() || !tgt.isActive())
//             throw new IllegalArgumentException("active");

//         List<CourseContentTopic> sTopics = topicRepo.findByCourseId(srcId);
//         List<CourseContentTopic> tTopics = topicRepo.findByCourseId(tgtId);

//         double overlap = 0;
//          for (CourseContentTopic s : sTopics)
//             for (CourseContentTopic t : tTopics)
//                 if (s.getTopicName().equalsIgnoreCase(t.getTopicName()))
//                      overlap += Math.min(s.getWeightPercentage(), t.getWeightPercentage());

//         TransferEvaluationResult r = new TransferEvaluationResult();
//         r.setOverlapPercentage(overlap == 0 ? 0.0 : overlap);
//         r.setIsEligibleForTransfer(false);
//         r.setNotes("No active transfer rule");

//         List<TransferRule> rules = ruleRepo
//                 .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
//                         src.getUniversity().getId(),
//                         tgt.getUniversity().getId()
//                 );

//         for (TransferRule rule : rules) {
//             if (overlap >= rule.getMinimumOverlapPercentage()) {
//                 int diff = Math.abs(src.getCreditHours() - tgt.getCreditHours());
//                 if (rule.getCreditHourTolerance() == null || diff <= rule.getCreditHourTolerance()) {
//                     r.setIsEligibleForTransfer(true);
//                     r.setNotes("Eligible");
//                 }
//             }
//         }

//         return resultRepo.save(r);
//     }

//     public TransferEvaluationResult getEvaluationById(Long id) {
//         return resultRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//     }

//     public List<TransferEvaluationResult> getEvaluationsForCourse(Long cid) {
//         return resultRepo.findBySourceCourseId(cid);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.TransferRule;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    CourseContentTopicRepository topicRepo;

    @Autowired
    TransferRuleRepository ruleRepo;

    @Autowired
    TransferEvaluationResultRepository resultRepo;

    public TransferEvaluationServiceImpl() {
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course src = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Source course not found"));

        Course tgt = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Target course not found"));

        if (!Boolean.TRUE.equals(src.getActive()) || !Boolean.TRUE.equals(tgt.getActive())) {
            throw new IllegalArgumentException("Course must be active");
        }

        List<CourseContentTopic> srcTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> tgtTopics = topicRepo.findByCourseId(targetCourseId);

        double matched = 0;
        double total = srcTopics.stream()
                .mapToDouble(t -> t.getWeightPercentage() == null ? 0 : t.getWeightPercentage())
                .sum();

        if (total == 0) {
            total = 100;
        }

        for (CourseContentTopic s : srcTopics) {
            for (CourseContentTopic t : tgtTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    matched += Math.min(
                            s.getWeightPercentage() == null ? 0 : s.getWeightPercentage(),
                            t.getWeightPercentage() == null ? 0 : t.getWeightPercentage()
                    );
                }
            }
        }

        double overlap = (matched / total) * 100;
        int creditDiff = Math.abs(src.getCreditHours() - tgt.getCreditHours());

        List<TransferRule> rules =
                ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        src.getUniversity().getId(),
                        tgt.getUniversity().getId()
                );

        boolean eligible = false;
        for (TransferRule r : rules) {
            if (overlap >= r.getMinimumOverlapPercentage()
                    && creditDiff <= (r.getCreditHourTolerance() == null ? 0 : r.getCreditHourTolerance())) {
                eligible = true;
                break;
            }
        }

        TransferEvaluationResult res = new TransferEvaluationResult();
        res.setOverlapPercentage(overlap);
        res.setCreditHourDifference(creditDiff);
        res.setIsEligibleForTransfer(eligible);
        res.setNotes(
                eligible ? "Eligible"
                        : rules.isEmpty() ? "No active transfer rule" : "No active rule satisfied"
        );

        return resultRepo.save(res);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long sourceCourseId) {
        return resultRepo.findBySourceCourseId(sourceCourseId);
    }
}