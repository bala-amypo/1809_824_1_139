package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {

    private final TransferRuleService service;

    public TransferRuleController(TransferRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TransferRule createRule(@RequestBody TransferRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public TransferRule updateRule(@PathVariable Long id, @RequestBody TransferRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public TransferRule getRuleById(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    public List<TransferRule> getRulesForPair(@PathVariable Long sourceId, @PathVariable Long targetId) {
        return service.getRulesForUniversities(sourceId, targetId);
    }

    @GetMapping("/active")
    public List<TransferRule> getActiveRules() {
        return service.getActiveRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateRule(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
