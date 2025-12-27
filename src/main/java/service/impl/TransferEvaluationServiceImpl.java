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
         for (CourseContentTopic s : sTopics)
            for (CourseContentTopic t : tTopics)
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName()))
                     overlap += Math.min(s.getWeightPercentage(), t.getWeightPercentage());

        TransferEvaluationResult r = new TransferEvaluationResult();
        r.setOverlapPercentage(overlap == 0 ? 0.0 : overlap);
        r.setIsEligibleForTransfer(false);
        r.setNotes("No active transfer rule");

        List<TransferRule> rules = ruleRepo
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        src.getUniversity().getId(),
                        tgt.getUniversity().getId()
                );

        for (TransferRule rule : rules) {
            if (overlap >= rule.getMinimumOverlapPercentage()) {
                int diff = Math.abs(src.getCreditHours() - tgt.getCreditHours());
                if (rule.getCreditHourTolerance() == null || diff <= rule.getCreditHourTolerance()) {
                    r.setIsEligibleForTransfer(true);
                    r.setNotes("Eligible");
                }
            }
        }

        return resultRepo.save(r);
    }

    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<TransferEvaluationResult> getEvaluationsForCourse(Long cid) {
        return resultRepo.findBySourceCourseId(cid);
    }
}
