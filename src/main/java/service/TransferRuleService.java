package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TransferRule;

public interface TransferRuleService {
    TransferRule save(TransferRule rule, Long universityId);
    List<TransferRule> getByUniversity(Long universityId);
}
