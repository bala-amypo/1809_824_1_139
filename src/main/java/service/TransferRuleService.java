package com.example.demo.service;

import com.example.demo.entity.TransferRule;

import java.util.List;

public interface TransferRuleService {
    TransferRule createRule(TransferRule rule);
    List<TransferRule> getRules(Long sourceUniversityId, Long targetUniversityId);
}
