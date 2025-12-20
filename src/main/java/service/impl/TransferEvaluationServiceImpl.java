package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.TransferEvaluationService;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.Course;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.CourseRepository;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository resultRepo;
    private final CourseRepository courseRepo;

    public TransferEvaluationServiceImpl(TransferEvaluationResultRepository resultRepo,
                                         CourseRepository courseRepo) {
        this.resultRepo = resultRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        Course sourceCourse = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course targetCourse = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Target course not found"));

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(sourceCourse);
        result.setTargetCourse(targetCourse);

        if (!sourceCourse.getActive() || !targetCourse.getActive()) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("Course not active");
        } else {
            // Example calculation logic
            double overlap = 100.0; // Replace with real topic overlap
            int creditDiff = Math.abs(sourceCourse.getCreditHours() - targetCourse.getCreditHours());

            result.setOverlapPercentage(overlap);
            result.setCreditHourDifference(creditDiff);

            if (overlap >= 50 && creditDiff <= 1) {
                result.setIsEligibleForTransfer(true);
                result.setNotes("Eligible for transfer");
            } else {
                result.setIsEligibleForTransfer(false);
                result.setNotes("No active rule satisfied");
            }
        }

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
