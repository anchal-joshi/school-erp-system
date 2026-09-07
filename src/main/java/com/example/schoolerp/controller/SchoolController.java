package com.example.schoolerp.controller;

import com.example.schoolerp.dto.SchoolRequest;
import com.example.schoolerp.dto.SchoolResponse;
import com.example.schoolerp.entity.School;
import com.example.schoolerp.entity.SchoolStatus;
import com.example.schoolerp.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SchoolResponse> registerSchool(@Valid @RequestBody SchoolRequest school){

        SchoolResponse response = service.registerSchool(school);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SchoolResponse> updateStatus(@PathVariable Long id,
                                                       @RequestBody SchoolStatus status){
        SchoolResponse schoolResponse = service.updateStatus(id, status);
        return ResponseEntity.ok(schoolResponse);
    }

    @GetMapping
    public List<SchoolResponse> getAllSchools(){
        List<SchoolResponse>responses = service.getAllSchools();
        return responses;
    }
}
