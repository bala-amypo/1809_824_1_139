package com.example.demo.controller;

import com.example.demo.dto.TransferRequestDTO;
import com.example.demo.dto.TransferResponseDTO;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
public class TransferEvaluationController {

    @Autowired
    private TransferEvaluationService evaluationService;

    @PostMapping("/evaluate")
    public ResponseEntity<TransferResponseDTO> evaluateTransfer(@RequestBody TransferRequestDTO request) {
        TransferResponseDTO response = evaluationService.evaluateTransfer(request);
        return ResponseEntity.ok(response);
    }
}
