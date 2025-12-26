// package com.example.demo.service.impl;

// import com.example.demo.entity.TransferRule;
// import com.example.demo.repository.TransferRuleRepository;
// import com.example.demo.service.TransferRuleService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class TransferRuleServiceImpl implements TransferRuleService {

//     private final TransferRuleRepository repo;

//     public TransferRuleServiceImpl(TransferRuleRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public TransferRule createRule(TransferRule rule) {
 //         rule.setActive(true);
//         return repo.save(rule);
//     }

//     @Override
//     public TransferRule updateRule(Long id, TransferRule rule) {
//         rule.setId(id);
//         return repo.save(rule);
//     }

//     @Override
//     public TransferRule getRuleById(Long id) {
//         return repo.findById(id).orElse(null);
//     }

//     @Override
//     public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
//         return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
//     }

//     @Override
//     public List<TransferRule> getActiveRules() {
 //         return repo.findByActiveTrue();
//     }

//     @Override
//     public void deactivateRule(Long id) {
//         TransferRule rule = repo.findById(id).orElse(null);
//         if (rule != null) {
//             rule.setActive(false);
//             repo.save(rule);
 //         }
//     }
 // }
 package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class TransferRuleServiceImpl {

    TransferRuleRepository repo;
    UniversityRepository univRepo;

    public TransferRule createRule(TransferRule r) {
        if (r.getMinimumOverlapPercentage() < 0 || r.getMinimumOverlapPercentage() > 100)
            throw new IllegalArgumentException("0-100");

        if (r.getCreditHourTolerance() != null && r.getCreditHourTolerance() < 0)
            throw new IllegalArgumentException(">= 0");

        if (r.getSourceUniversity() != null)
            univRepo.findById(r.getSourceUniversity().getId());

        if (r.getTargetUniversity() != null)
            univRepo.findById(r.getTargetUniversity().getId());

        return repo.save(r);
    }

    public TransferRule updateRule(Long id, TransferRule r) {
        repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(r);
    }

    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
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
}

