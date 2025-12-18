package com.example.demo.service;
import com.example.demo.entity.TranferEvaluation;
public interface TransferEvaluationService{
    evaluate Transfer(Long sourceCourseId,Long targetCourseId);
    getEvaluationById(Long id);
    getEvaluationForCourse(Long courseId);
}