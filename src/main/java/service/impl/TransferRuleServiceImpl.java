package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferRuleService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository repo;
    private final UniversityRepository universityRepo;

    public TransferRuleServiceImpl(TransferRuleRepository repo, UniversityRepository universityRepo) {
        this.repo = repo;
        this.universityRepo = universityRepo;
    }

    @Override
    public TransferRule createRule(TransferRule r) {
        if (r.getSourceUniversity() == null || r.getTargetUniversity() == null)
            throw new IllegalArgumentException("Source and Target universities required");

        universityRepo.findById(r.getSourceUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Source university not found"));

        universityRepo.findById(r.getTargetUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Target university not found"));

        if (r.getCourseCode() == null || r.getCourseCode().isBlank())
            throw new IllegalArgumentException("Course code required");

        if (r.getCreditTransferPercentage() < 0 || r.getCreditTransferPercentage() > 100)
            throw new IllegalArgumentException("Credit transfer must be 0-100");

        if (repo.existsBySourceUniversityIdAndTargetUniversityIdAndCourseCode(
                r.getSourceUniversity().getId(),
                r.getTargetUniversity().getId(),
                r.getCourseCode()
        )) throw new IllegalArgumentException("Rule already exists");

        r.setActive(true);
        return repo.save(r);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule r) {
        TransferRule existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        existing.setCourseCode(r.getCourseCode());
        existing.setCreditTransferPercentage(r.getCreditTransferPercentage());

        return repo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceUniversityId, Long targetUniversityId) {
        universityRepo.findById(sourceUniversityId)
                .orElseThrow(() -> new RuntimeException("Source university not found"));

        universityRepo.findById(targetUniversityId)
                .orElseThrow(() -> new RuntimeException("Target university not found"));

        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceUniversityId, targetUniversityId);
    }

    @Override
    public TransferRule deactivateRule(Long id) {
        TransferRule existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        existing.setActive(false);
        return repo.save(existing);
    }
}
