package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class TransferEvaluationController {

    private final TransferEvaluationService service;

    public TransferEvaluationController(TransferEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/{src}/{tgt}")
    public TransferEvaluationResult evaluate(@PathVariable Long src, @PathVariable Long tgt) {
        return service.evaluateTransfer(src, tgt);
    }

    @GetMapping("/{id}")
    public TransferEvaluationResult get(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    @GetMapping("/course/{cid}")
    public List<TransferEvaluationResult> byCourse(@PathVariable Long cid) {
        return service.getEvaluationsForCourse(cid);
    }
}
