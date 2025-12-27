// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;

// import java.util.List;

// public class TransferRuleServiceImpl {

//     private TransferRuleRepository repo;
//     private UniversityRepository univRepo;

//     public TransferRule createRule(TransferRule r) {
//         if (r.getMinimumOverlapPercentage() < 0 || r.getMinimumOverlapPercentage() > 100)
//             throw new IllegalArgumentException("0-100");
//         if (r.getCreditHourTolerance() != null && r.getCreditHourTolerance() < 0)
//             throw new IllegalArgumentException(">= 0");
//         univRepo.findById(r.getSourceUniversity().getId()).orElseThrow();
//         univRepo.findById(r.getTargetUniversity().getId()).orElseThrow();
//         return repo.save(r);
//     }

//     public List<TransferRule> getRulesForUniversities(Long s, Long t) {
//         return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(s, t);
//     }

//     public void deactivateRule(Long id) {
//         TransferRule r = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//         r.setActive(false);
//         repo.save(r);
//     }

//     public TransferRule getRuleById(Long id) {
//         return repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//     }

//     public TransferRule updateRule(Long id, TransferRule r) {
//         repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//         return repo.save(r);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    @Autowired
    TransferRuleRepository repo;

    @Autowired
    UniversityRepository univRepo;

    public TransferRuleServiceImpl() {
    }

    @Override
    public TransferRule createRule(TransferRule rule) {

        if (rule.getMinimumOverlapPercentage() == null ||
                rule.getMinimumOverlapPercentage() < 0 ||
                rule.getMinimumOverlapPercentage() > 100) {
            throw new IllegalArgumentException("Overlap must be 0-100");
        }

        if (rule.getCreditHourTolerance() != null && rule.getCreditHourTolerance() < 0) {
            throw new IllegalArgumentException("Credit tolerance must be >= 0");
        }

        univRepo.findById(rule.getSourceUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Source university not found"));

        univRepo.findById(rule.getTargetUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Target university not found"));

        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        return repo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        r.setActive(false);
        repo.save(r);
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long s, Long t) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(s, t);
    }
}