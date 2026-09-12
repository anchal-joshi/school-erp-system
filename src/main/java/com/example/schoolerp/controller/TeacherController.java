package com.example.schoolerp.controller;

import com.example.schoolerp.dto.TeacherRequest;
import com.example.schoolerp.dto.TeacherResponse;
import com.example.schoolerp.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<TeacherResponse> createTeacher(@Valid @RequestBody TeacherRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(request));
    }
}
