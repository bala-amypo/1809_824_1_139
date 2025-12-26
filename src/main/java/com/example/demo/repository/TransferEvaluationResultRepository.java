package com.example.demo.repository;

import java.util.List;


import com.example.demo.entity.TransferEvaluationResult;

public interface TransferEvaluationResultRepository extends JpaRepository<TransferEvaluationResult, Long> {
    List<TransferEvaluationResult> findBySourceCourseId(Long sourceCourseId);
}
