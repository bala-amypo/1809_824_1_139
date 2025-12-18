package com.example.demo.service;
import com.example.demo.entity.TransferEvaluation;
import java.util.List;
public interface TransferEvaluationService {
    TransferEvaluation evaluateTransfer(Long sourceCourseId, Long targetCourseId);
    TransferEvaluation getEvaluationById(Long id);
    List<TransferEvaluation> getEvaluationForCourse(Long courseId);
}