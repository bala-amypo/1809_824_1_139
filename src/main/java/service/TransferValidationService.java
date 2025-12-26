package com.example.demo.service;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;

public interface TransferValidationService {
    TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request);
}
