package com.example.schoolerp.controller;

import com.example.schoolerp.dto.ChangePasswordRequest;
import com.example.schoolerp.dto.StudentRequest;
import com.example.schoolerp.dto.StudentResponse;
import com.example.schoolerp.dto.StudentUpdateRequest;
import com.example.schoolerp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createStudent(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,
                                                         @Valid @RequestBody StudentUpdateRequest request){
        return ResponseEntity.ok(service.updateStudent(id, request));
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> changePassword(@PathVariable Long id,
                                                 @RequestBody ChangePasswordRequest request){
        return ResponseEntity.ok(service.changePassword(request));
    }
}
