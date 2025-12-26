package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.entity.University;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository ruleRepo;
    private final UniversityRepository universityRepo;

    public TransferRuleServiceImpl(TransferRuleRepository ruleRepo, UniversityRepository universityRepo) {
        this.ruleRepo = ruleRepo;
        this.universityRepo = universityRepo;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        // Validate universities exist
        University src = universityRepo.findById(rule.getSourceUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Source university not found"));
        University tgt = universityRepo.findById(rule.getTargetUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Target university not found"));

        rule.setSourceUniversity(src);
        rule.setTargetUniversity(tgt);
        rule.setActive(true);
        return ruleRepo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = ruleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer rule not found"));
        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        existing.setActive(rule.getActive());
        return ruleRepo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return ruleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceUniversityId, Long targetUniversityId) {
        return ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceUniversityId, targetUniversityId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule existing = ruleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer rule not found"));
        existing.setActive(false);
        ruleRepo.save(existing);
    }
}
