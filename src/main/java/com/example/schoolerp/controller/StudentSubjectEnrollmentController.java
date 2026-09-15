package com.example.schoolerp.controller;

import com.example.schoolerp.dto.StudentSubjectEnrollmentRequest;
import com.example.schoolerp.dto.StudentSubjectEnrollmentResponse;
import com.example.schoolerp.service.StudentSubjectEnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class StudentSubjectEnrollmentController {

    private final StudentSubjectEnrollmentService service;

    public StudentSubjectEnrollmentController(StudentSubjectEnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<StudentSubjectEnrollmentResponse> create(@Valid @RequestBody StudentSubjectEnrollmentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<StudentSubjectEnrollmentResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<StudentSubjectEnrollmentResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
