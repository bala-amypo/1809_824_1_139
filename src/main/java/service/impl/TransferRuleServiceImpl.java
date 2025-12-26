package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class TransferRuleServiceImpl {

    private TransferRuleRepository repo;
    private UniversityRepository univRepo;

    public TransferRule createRule(TransferRule r) {
        if (r.getMinimumOverlapPercentage() < 0 || r.getMinimumOverlapPercentage() > 100)
            throw new IllegalArgumentException("0-100");

        if (r.getCreditHourTolerance() != null && r.getCreditHourTolerance() < 0)
            throw new IllegalArgumentException(">= 0");

        if (r.getSourceUniversity() != null)
            univRepo.findById(r.getSourceUniversity().getId()).orElseThrow();

        if (r.getTargetUniversity() != null)
            univRepo.findById(r.getTargetUniversity().getId()).orElseThrow();

        return repo.save(r);
    }

    public List<TransferRule> getRulesForUniversities(Long s, Long t) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(s, t);
    }

    public void deactivateRule(Long id) {
        TransferRule r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        r.setActive(false);
        repo.save(r);
    }

    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public TransferRule updateRule(Long id, TransferRule r) {
        repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(r);
    }
}
