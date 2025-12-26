package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TransferEvaluationServiceImpl {
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;
    private TransferEvaluationResultRepository resultRepo;

    public TransferEvaluationResult evaluateTransfer(Long srcId, Long tgtId) {
        Course src = courseRepo.findById(srcId).orElseThrow(() -> new RuntimeException("not found"));
        Course tgt = courseRepo.findById(tgtId).orElseThrow(() -> new RuntimeException("not found"));
        
        if (!src.isActive() || !tgt.isActive()) throw new IllegalArgumentException("Courses must be active");

        List<CourseContentTopic> srcTopics = topicRepo.findByCourseId(srcId);
        List<CourseContentTopic> tgtTopics = topicRepo.findByCourseId(tgtId);

        double overlap = 0.0;
        for (CourseContentTopic st : srcTopics) {
            for (CourseContentTopic tt : tgtTopics) {
                if (st.getTopicName().equalsIgnoreCase(tt.getTopicName())) {
                    overlap += Math.min(st.getWeightPercentage(), tt.getWeightPercentage());
                }
            }
        }

        TransferEvaluationResult res = new TransferEvaluationResult();
        res.setSourceCourseId(srcId);
        res.setTargetCourseId(tgtId);
        res.setOverlapPercentage(overlap == 0 && srcTopics.isEmpty() ? 0.0 : overlap);
        
        List<TransferRule> rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
            src.getUniversity().getId(), tgt.getUniversity().getId());

        boolean eligible = false;
        String notes = "No active transfer rule found.";
        
        for (TransferRule rule : rules) {
            boolean overlapOk = overlap >= rule.getMinimumOverlapPercentage();
            boolean creditOk = Math.abs(src.getCreditHours() - tgt.getCreditHours()) <= rule.getCreditHourTolerance();
            if (overlapOk && creditOk) {
                eligible = true;
                notes = "Eligible under rule " + rule.getId();
                break;
            } else {
                notes = "No active rule satisfied (Overlap/Credit mismatch)";
            }
        }

        res.setIsEligibleForTransfer(eligible);
        res.setNotes(notes);
        return resultRepo.save(res);
    }

    public List<TransferEvaluationResult> getEvaluationsForCourse(Long id) { return resultRepo.findBySourceCourseId(id); }
    public TransferEvaluationResult getEvaluationById(Long id) { return resultRepo.findById(id).orElseThrow(() -> new RuntimeException("not found")); }
}