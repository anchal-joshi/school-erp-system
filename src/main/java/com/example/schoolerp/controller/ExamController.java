package com.example.schoolerp.controller;

import com.example.schoolerp.dto.ExamRequest;
import com.example.schoolerp.dto.ExamResponse;
import com.example.schoolerp.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<ExamResponse> createExam(@Valid @RequestBody ExamRequest examRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(examRequest));
    }

    @GetMapping("/{examId}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<ExamResponse> getExam(@PathVariable Long examId){
        return ResponseEntity.ok(examService.getExamById(examId));
    }

    @PutMapping("/{examId}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<ExamResponse> updateExam(@PathVariable Long examId,
                                                   @Valid @RequestBody ExamRequest examRequest){
        return ResponseEntity.ok(examService.updateExam(examId, examRequest));
    }

    @DeleteMapping("/{examId}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long examId){
        examService.delete(examId);
        return ResponseEntity.noContent().build();
    }

}
