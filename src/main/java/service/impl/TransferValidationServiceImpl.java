package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.TransferValidationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    private final ProgramRepository programRepository;
    private final CourseRepository courseRepository;
    private final CourseMappingRepository mappingRepository;
    private final TransferRequestRepository transferRequestRepository;

    public TransferValidationServiceImpl(
            ProgramRepository programRepository,
            CourseRepository courseRepository,
            CourseMappingRepository mappingRepository,
            TransferRequestRepository transferRequestRepository
    ) {
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
        this.mappingRepository = mappingRepository;
        this.transferRequestRepository = transferRequestRepository;
    }

    @Override
    public TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request) {
        Program sourceProgram = programRepository.findById(request.getSourceProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Source Program", request.getSourceProgramId()));
        Program targetProgram = programRepository.findById(request.getTargetProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Target Program", request.getTargetProgramId()));

        double totalTransferableCredits = 0;
        List<String> acceptedCourses = new ArrayList<>();
        List<String> missingRequirements = new ArrayList<>();

        for (var completed : request.getCompletedCourses()) {
            Course sourceCourse = courseRepository.findByCodeIgnoreCase(completed.getCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Course", completed.getCode()));

            List<CourseMapping> mappings = mappingRepository.findBySourceCourseId(sourceCourse.getId());
            boolean mapped = false;

            for (CourseMapping mapping : mappings) {
                if (mapping.getTargetCourse().getId().equals(targetProgram.getId())) {
                    totalTransferableCredits += sourceCourse.getCredits();
                    acceptedCourses.add(sourceCourse.getCode());
                    mapped = true;
                    break;
                }
            }

            if (!mapped) {
                missingRequirements.add(sourceCourse.getCode());
            }
        }

        TransferEvaluationResponse response = new TransferEvaluationResponse();
        response.setTotalTransferableCredits(totalTransferableCredits);
        response.setAcceptedCourses(acceptedCourses);
        response.setMissingCourses(missingRequirements);
        response.setStatus(missingRequirements.isEmpty() ? "APPROVED" : "REJECTED");
        response.setRemarks(missingRequirements.isEmpty() ? "All requirements met" : "Missing courses found");

        return response;
    }
}
