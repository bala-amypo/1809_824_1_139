package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.TransferRule;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;

public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    // Injected via reflection in test
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;
    private TransferEvaluationResultRepository resultRepo;

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!source.isActive() || !target.isActive()) {
            throw new IllegalArgumentException("Course must be active");
        }

        List<CourseContentTopic> sourceTopics =
                topicRepo.findByCourseId(sourceCourseId);

        List<CourseContentTopic> targetTopics =
                topicRepo.findByCourseId(targetCourseId);

        double matchedWeight = 0;
        double totalWeight = 0;

        for (CourseContentTopic s : sourceTopics) {
            totalWeight += s.getWeightPercentage();
            for (CourseContentTopic t : targetTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    matchedWeight += Math.min(
                            s.getWeightPercentage(),
                            t.getWeightPercentage()
                    );
                }
            }
        }

        double overlapPercentage =
                totalWeight == 0 ? 0 : (matchedWeight / totalWeight) * 100;

        List<TransferRule> rules =
                ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId()
                );

        boolean eligible = false;
        String notes = "No active transfer rule satisfied";

        for (TransferRule r : rules) {
            double creditDiff =
                    Math.abs(source.getCreditHours() - target.getCreditHours());

            if (overlapPercentage >= r.getMinimumOverlapPercentage()
                    && creditDiff <= r.getCreditHourTolerance()) {
                eligible = true;
                notes = "Eligible for transfer";
                break;
            }
        }

        if (rules.isEmpty()) {
            notes = "No active transfer rule";
        }

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);
        result.setOverlapPercentage(overlapPercentage);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(notes);

        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}
