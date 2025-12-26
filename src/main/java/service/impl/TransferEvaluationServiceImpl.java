package com.example.demo.service.impl;

import com.example.demo.dto.TransferRequestDTO;
import com.example.demo.dto.TransferResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    @Autowired
    private CourseContentTopicRepository topicRepository;

    @Autowired
    private TransferRuleRepository ruleRepository;

    @Autowired
    private TransferEvaluationResultRepository resultRepository;

    @Override
    public TransferResponseDTO evaluateTransfer(TransferRequestDTO request) {
        List<TransferRule> rules = ruleRepository.findBySourceUniversityIdAndTargetUniversityId(
                request.getSourceUniversityId(),
                request.getTargetUniversityId()
        );

        // Dummy evaluation logic
        double overlap = 80.0;  // placeholder
        boolean eligible = overlap >= rules.get(0).getMinimumOverlapPercentage();

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setIsEligibleForTransfer(eligible);
        result.setOverlapPercentage(overlap);
        result.setNotes("Automatically generated result");

        resultRepository.save(result);

        return new TransferResponseDTO(eligible, overlap, "Transfer evaluated");
    }
}
