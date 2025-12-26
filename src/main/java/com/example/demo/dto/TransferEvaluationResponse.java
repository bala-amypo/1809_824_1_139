package com.example.demo.dto;

import java.util.List;

public class TransferEvaluationResponse {
    private Double totalTransferableCredits;
    private List<AcceptedCourseDTO> acceptedCourses;
    private List<String> missingRequirements;
    private String status;
    private String remarks;

    public TransferEvaluationResponse() {}

    public TransferEvaluationResponse(Double totalTransferableCredits, List<AcceptedCourseDTO> acceptedCourses,
                                      List<String> missingRequirements, String status, String remarks) {
        this.totalTransferableCredits = totalTransferableCredits;
        this.acceptedCourses = acceptedCourses;
        this.missingRequirements = missingRequirements;
        this.status = status;
        this.remarks = remarks;
    }

    public Double getTotalTransferableCredits() {
        return totalTransferableCredits;
    }

    public void setTotalTransferableCredits(Double totalTransferableCredits) {
        this.totalTransferableCredits = totalTransferableCredits;
    }

    public List<AcceptedCourseDTO> getAcceptedCourses() {
        return acceptedCourses;
    }

    public void setAcceptedCourses(List<AcceptedCourseDTO> acceptedCourses) {
        this.acceptedCourses = acceptedCourses;
    }

    public List<String> getMissingRequirements() {
        return missingRequirements;
    }

    public void setMissingRequirements(List<String> missingRequirements) {
        this.missingRequirements = missingRequirements;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Inner DTO for accepted courses
    public static class AcceptedCourseDTO {
        private String sourceCourseCode;
        private String targetCourseCode;
        private Double credits;

        public AcceptedCourseDTO() {}

        public AcceptedCourseDTO(String sourceCourseCode, String targetCourseCode, Double credits) {
            this.sourceCourseCode = sourceCourseCode;
            this.targetCourseCode = targetCourseCode;
            this.credits = credits;
        }

        public String getSourceCourseCode() {
            return sourceCourseCode;
        }

        public void setSourceCourseCode(String sourceCourseCode) {
            this.sourceCourseCode = sourceCourseCode;
        }

        public String getTargetCourseCode() {
            return targetCourseCode;
        }

        public void setTargetCourseCode(String targetCourseCode) {
            this.targetCourseCode = targetCourseCode;
        }

        public Double getCredits() {
            return credits;
        }

        public void setCredits(Double credits) {
            this.credits = credits;
        }
    }
}
