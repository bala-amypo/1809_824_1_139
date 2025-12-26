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
    public TransferRule create(@RequestBody TransferRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public TransferRule update(@PathVariable Long id, @RequestBody TransferRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public TransferRule get(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping("/universities/{src}/{tgt}")
    public List<TransferRule> getRules(@PathVariable Long src, @PathVariable Long tgt) {
        return service.getRulesForUniversities(src, tgt);
    }

    @PatchMapping("/deactivate/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
