// package com.example.demo.controller;

// import com.example.demo.entity.TransferRule;
// import com.example.demo.service.TransferRuleService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/rules")
// public class TransferRuleController {

//     @Autowired
//     private TransferRuleService ruleService;

//     @PostMapping
//     public ResponseEntity<TransferRule> createRule(@RequestBody TransferRule rule) {
//         TransferRule created = ruleService.createRule(rule);
//         return ResponseEntity.ok(created);
//     }

//     @GetMapping
//     public ResponseEntity<List<TransferRule>> getRules(@RequestParam Long sourceUniversityId,
//                                                        @RequestParam Long targetUniversityId) {
//         List<TransferRule> rules = ruleService.getRules(sourceUniversityId, targetUniversityId);
//         return ResponseEntity.ok(rules);
//     }
// }
