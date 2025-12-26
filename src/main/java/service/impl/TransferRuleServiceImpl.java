package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    @Autowired
    private TransferRuleRepository ruleRepository;

    @Override
    public TransferRule createRule(TransferRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<TransferRule> getRules(Long sourceUniversityId, Long targetUniversityId) {
        return ruleRepository.findBySourceUniversityIdAndTargetUniversityId(sourceUniversityId, targetUniversityId);
    }
}
