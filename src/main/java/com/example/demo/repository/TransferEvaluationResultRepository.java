package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.TransferEvaluationResult;

public interface TransferEvaluationResultRepository {

    TransferEvaluationResult save(TransferEvaluationResult result);

    Optional<TransferEvaluationResult> findById(Long id);

    List<TransferEvaluationResult> findBySourceCourseId(Long courseId);
}
