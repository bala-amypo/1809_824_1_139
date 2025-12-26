package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class TransferEvaluationServiceImpl {

    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;
    private TransferEvaluationResultRepository resultRepo;

    public TransferEvaluationResult evaluateTransfer(Long srcId, Long tgtId) {

        Course src = courseRepo.findById(srcId).orElseThrow();
        Course tgt = courseRepo.findById(tgtId).orElseThrow();

        if (!src.isActive() || !tgt.isActive())
            throw new IllegalArgumentException("active");

        List<CourseContentTopic> sTopics = topicRepo.findByCourseId(srcId);
        List<CourseContentTopic> tTopics = topicRepo.findByCourseId(tgtId);

        double overlap = 0;
        for (CourseContentTopic s : sTopics) {
            for (CourseContentTopic t : tTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    overlap += Math.min(s.getWeightPercentage(), t.getWeightPercentage());
                }
            }
        }

        TransferEvaluationResult res = new TransferEvaluationResult();
        res.setOverlapPercentage(overlap);
        res.setIsEligibleForTransfer(false);
        res.setNotes("No active transfer rule");

        List<TransferRule> rules =
                ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        src.getUniversity().getId(),
                        tgt.getUniversity().getId()
                );

        for (TransferRule r : rules) {
            int diff = Math.abs(src.getCreditHours() - tgt.getCreditHours());
            if (overlap >= r.getMinimumOverlapPercentage()
                    && diff <= r.getCreditHourTolerance()) {
                res.setIsEligibleForTransfer(true);
                res.setNotes("Eligible");
            }
        }

        return resultRepo.save(res);
    }

    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}
