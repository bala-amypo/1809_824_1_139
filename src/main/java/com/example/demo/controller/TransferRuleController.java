package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.TransferRule;
import com.example.demo.service.impl.TransferRuleServiceImpl;

@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {

    private final TransferRuleServiceImpl service;

    public TransferRuleController(TransferRuleServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public TransferRule create(@RequestBody TransferRule r) {
        return service.createRule(r);
    }

    @GetMapping("/{id}")
    public TransferRule get(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping
    public List<TransferRule> getRules(
            @RequestParam Long sourceUniversityId,
            @RequestParam Long targetUniversityId) {
        return service.getRulesForUniversities(sourceUniversityId, targetUniversityId);
    }

    @PutMapping("/{id}")
    public TransferRule update(@PathVariable Long id, @RequestBody TransferRule r) {
        return service.updateRule(id, r);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
