package com.example.demo.controller;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.service.TransferValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferEvaluationController {

    private final TransferValidationService transferService;

    public TransferEvaluationController(TransferValidationService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/evaluate")
    @PreAuthorize("hasAnyRole('EVALUATOR', 'ADMIN')")
    public ResponseEntity<TransferEvaluationResponse> evaluate(@RequestBody TransferEvaluationRequest request) {
        TransferEvaluationResponse response = transferService.evaluateTransfer(request);
        return ResponseEntity.ok(response);
    }

    // Optional: Endpoint for listing previous transfers
    // @GetMapping("/history/{studentId}")
    // public ResponseEntity<List<TransferEvaluationResponse>> history(@PathVariable String studentId) {
    //     // Implement if required by test cases
    // }
}
