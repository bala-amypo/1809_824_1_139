package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import java.util.List;

public interface TransferRuleService {
    TransferRule createRule(TransferRule r);
    TransferRule updateRule(Long id, TransferRule r);
    TransferRule getRuleById(Long id);
    List<TransferRule> getRulesForUniversities(Long sourceUniversityId, Long targetUniversityId);
    void deactivateRule(Long id);
}
