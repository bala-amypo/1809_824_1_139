package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;

public interface TransferEvaluationService {
    TransferEvaluationResult evaluate(Long sourceCourseId, Long targetCourseId);
}
