package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.impl.TransferRuleServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class TransferRuleController {

    private final TransferRuleServiceImpl service = new TransferRuleServiceImpl();

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

    @GetMapping("/{s}/{t}")
    public List<TransferRule> rules(@PathVariable Long s, @PathVariable Long t) {
        return service.getRulesForUniversities(s, t);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
