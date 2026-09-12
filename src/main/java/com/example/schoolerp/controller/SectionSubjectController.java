package com.example.schoolerp.controller;

import com.example.schoolerp.dto.SectionSubjectRequest;
import com.example.schoolerp.dto.SectionSubjectResponse;
import com.example.schoolerp.service.SectionSubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectionsubject")
public class SectionSubjectController {

    private final SectionSubjectService service;

    public SectionSubjectController(SectionSubjectService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<SectionSubjectResponse> create(@RequestBody SectionSubjectRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSectionSubject(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<SectionSubjectResponse>> getSubjectsBySection(@PathVariable Long id){
        return ResponseEntity.ok(service.getSubjectsBySection(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

}
