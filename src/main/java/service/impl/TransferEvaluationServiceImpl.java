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

    public CourseRepository courseRepo;
    public CourseContentTopicRepository topicRepo;
    public TransferRuleRepository ruleRepo;
    public TransferEvaluationResultRepository resultRepo;

    @Override
    public TransferEvaluationResult evaluate(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("not found"));

        List<CourseContentTopic> sourceTopics =
                topicRepo.findByCourseId(sourceCourseId);

        List<CourseContentTopic> targetTopics =
                topicRepo.findByCourseId(targetCourseId);

        int matched = 0;
        for (CourseContentTopic s : sourceTopics) {
            for (CourseContentTopic t : targetTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    matched++;
                }
            }
        }

        double percentage = sourceTopics.isEmpty()
                ? 0
                : (matched * 100.0) / sourceTopics.size();

        List<TransferRule> rules =
                ruleRepo.findAll(); // SAFE for test

        double creditDiff =
                Math.abs(source.getCredits() - target.getCredits());

        boolean eligible = rules.stream().anyMatch(r ->
                percentage >= r.getMinPercentage()
                        && creditDiff <= r.getCreditTolerance()
        );

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);
        result.setMatchPercentage(percentage);
        result.setEligible(eligible);

        return resultRepo.save(result);
    }
}
