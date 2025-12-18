package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TransferEvaluationResult;
public interface TransferEvaluationResultRepository extends JpaRepository<TransferEvaluationResult,Long>{
    
}