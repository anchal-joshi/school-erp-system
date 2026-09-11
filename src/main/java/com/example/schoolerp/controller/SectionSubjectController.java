package com.example.schoolerp.controller;

import com.example.schoolerp.dto.SectionSubjectRequest;
import com.example.schoolerp.dto.SectionSubjectResponse;
import com.example.schoolerp.service.SectionSubjectService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sectionsubject")
public class SectionSubjectController {

    private final SectionSubjectService service;

    public SectionSubjectController(SectionSubjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SectionSubjectResponse> create(@RequestBody SectionSubjectRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSectionSubject(request));
    }


}
