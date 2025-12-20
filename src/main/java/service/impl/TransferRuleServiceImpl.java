package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository repo;

    public TransferRuleServiceImpl(TransferRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        rule.setActive(true);
        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        rule.setId(id);
        return repo.save(rule);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
    }

    @Override
    public List<TransferRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = repo.findById(id).orElse(null);
        if (rule != null) {
            rule.setActive(false);
            repo.save(rule);
        }
    }
}
