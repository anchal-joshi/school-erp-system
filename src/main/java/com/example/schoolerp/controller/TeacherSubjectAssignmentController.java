package com.example.schoolerp.controller;

import com.example.schoolerp.dto.TeacherSubjectAssignmentRequest;
import com.example.schoolerp.dto.TeacherSubjectAssignmentResponse;
import com.example.schoolerp.service.TeacherSubjectAssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-subject-assignment")
public class TeacherSubjectAssignmentController {

    private final TeacherSubjectAssignmentService service;

    public TeacherSubjectAssignmentController(TeacherSubjectAssignmentService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<TeacherSubjectAssignmentResponse> create(@Valid @RequestBody TeacherSubjectAssignmentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<TeacherSubjectAssignmentResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<TeacherSubjectAssignmentResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
