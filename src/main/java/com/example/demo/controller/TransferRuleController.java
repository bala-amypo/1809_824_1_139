package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rules")
public class TransferRuleController {

    private final TransferRuleService service;

    public TransferRuleController(TransferRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TransferRule create(@RequestBody TransferRule r) {
        return service.createRule(r);
    }

    @PutMapping("/{id}")
    public TransferRule update(@PathVariable Long id, @RequestBody TransferRule r) {
        return service.updateRule(id, r);
    }

    @GetMapping("/{id}")
    public TransferRule get(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping("/universities")
    public List<TransferRule> byUniversities(@RequestParam Long sourceId, @RequestParam Long targetId) {
        return service.getRulesForUniversities(sourceId, targetId);
    }

    @DeleteMapping("/{id}")
    public TransferRule deactivate(@PathVariable Long id) {
        return service.deactivateRule(id);
    }
}
