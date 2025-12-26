package com.example.demo.service;

import com.example.demo.dto.TransferRequestDTO;
import com.example.demo.dto.TransferResponseDTO;

public interface TransferEvaluationService {
    TransferResponseDTO evaluateTransfer(TransferRequestDTO request);
}
