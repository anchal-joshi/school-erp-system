package com.example.schoolerp.controller;

import com.example.schoolerp.dto.ClassRequest;
import com.example.schoolerp.dto.ClassResponse;
import com.example.schoolerp.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<ClassResponse> createClass(@Valid @RequestBody ClassRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(classService.createClass(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<List<ClassResponse>> getAllClasses(){
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<ClassResponse> getClassById(@PathVariable Long id) throws ClassNotFoundException {
        return ResponseEntity.ok(classService.getClassById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public void deleteClassById(@PathVariable Long id){
        classService.deleteClassById(id);
    }
}
