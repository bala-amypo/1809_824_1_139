package com.example.demo.service;
import com.example.demo.entity.TranferEvaluation;
public interface TransferEvaluationService{
    TransferEvaluation evaluate Transfer(Long sourceCourseId,Long targetCourseId);
    TransferEvaluation getEvaluationById(Long id);
    List<TransferEvaluation>getEvaluationForCourse(Long courseId);
}