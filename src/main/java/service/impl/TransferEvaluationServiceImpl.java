package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final CourseRepository courseRepo;
    private final CourseContentTopicRepository topicRepo;
    private final TransferRuleRepository ruleRepo;
    private final TransferEvaluationResultRepository resultRepo;

    public TransferEvaluationServiceImpl(CourseRepository courseRepo,
                                         CourseContentTopicRepository topicRepo,
                                         TransferRuleRepository ruleRepo,
                                         TransferEvaluationResultRepository resultRepo) {
        this.courseRepo = courseRepo;
        this.topicRepo = topicRepo;
        this.ruleRepo = ruleRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long srcId, Long tgtId) {
        Course src = courseRepo.findById(srcId)
                .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course tgt = courseRepo.findById(tgtId)
                .orElseThrow(() -> new RuntimeException("Target course not found"));

        if (!src.getActive() || !tgt.getActive()) {
            throw new IllegalArgumentException("Both courses must be active");
        }

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

        int creditDiff = Math.abs(src.getCreditHours() - tgt.getCreditHours());

        List<TransferRule> rules = ruleRepo
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        src.getUniversity().getId(),
                        tgt.getUniversity().getId()
                );

        boolean eligible = false;
        for (TransferRule r : rules) {
            double minOverlap = Optional.ofNullable(r.getMinimumOverlapPercentage()).orElse(0.0);
            int tolerance = Optional.ofNullable(r.getCreditHourTolerance()).orElse(0);
            if (overlap >= minOverlap && creditDiff <= tolerance) {
                eligible = true;
                break;
            }
        }

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(src);
        result.setTargetCourse(tgt);
        result.setOverlapPercentage(overlap);
        result.setCreditHourDifference(creditDiff);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(eligible ? "Eligible for transfer" :
                rules.isEmpty() ? "No active transfer rule" : "No active rule satisfied");

        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}
