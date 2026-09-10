package com.example.schoolerp.controller;

import com.example.schoolerp.dto.SectionRequest;
import com.example.schoolerp.dto.SectionResponse;
import com.example.schoolerp.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<SectionResponse> createSection(@Valid @RequestBody SectionRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionService.createSection(request));
    }

    @GetMapping("/{classId}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<SectionResponse>> getSectionByClass(@PathVariable Long classId){
        return ResponseEntity.ok(sectionService.getSectionByClass(classId));
    }
    @GetMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<SectionResponse>> getAllSections(){
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public void delete(@PathVariable Long id){
        sectionService.deleteById(id);
    }
}
