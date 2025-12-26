// package com.example.demo.controller;

// import com.example.demo.entity.TransferEvaluationResult;
// import com.example.demo.service.impl.TransferEvaluationServiceImpl;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/evaluations")
// public class TransferEvaluationController {

//     @Autowired
//     private TransferEvaluationServiceImpl evaluationService;

//     /**
//      * Triggers the automated evaluation between two courses based on topic overlap and rules.
//      */
//     @PostMapping("/evaluate")
//     public ResponseEntity<TransferEvaluationResult> evaluate(@RequestParam Long sourceCourseId, 
//                                                             @RequestParam Long targetCourseId) {
//         return ResponseEntity.ok(evaluationService.evaluateTransfer(sourceCourseId, targetCourseId));
//     }

//     /**
//      * Get a specific evaluation result by ID.
//      */
//     @GetMapping("/{id}")
//     public ResponseEntity<TransferEvaluationResult> getById(@PathVariable Long id) {
//         return ResponseEntity.ok(evaluationService.getEvaluationById(id));
//     }

//     /**
//      * Get all evaluation history for a specific source course.
//      */
//     @GetMapping("/course/{courseId}")
//     public ResponseEntity<List<TransferEvaluationResult>> getByCourse(@PathVariable Long courseId) {
//         return ResponseEntity.ok(evaluationService.getEvaluationsForCourse(courseId));
//     }
// }