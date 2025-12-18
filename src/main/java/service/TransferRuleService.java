package com.example.demo.service;
import com.example.demo.entity.TransferRule;
public interface TransferRuleService{
    createRule(TransferRule rule);
    updateRule(Long id,TransferRule rule);
    getRuleById(Long id);
    getRulesForUniversities(Long sourceId,Long targetId);
    void deactivateRule(Long id);

}