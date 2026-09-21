package com.example.schoolerp.controller;

import com.example.schoolerp.dto.MarksRequest;
import com.example.schoolerp.dto.MarksResponse;
import com.example.schoolerp.service.MarksService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marks")
public class MarksController {

    private final MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<MarksResponse> createMarks(@Valid @RequestBody MarksRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(marksService.createMarks(request));
    }

    @GetMapping("/{marksId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<MarksResponse> getMarks(@PathVariable Long marksId){
        return ResponseEntity.ok(marksService.getMarks(marksId));
    }

    @PutMapping("/{marksId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<MarksResponse> updateMarks(@PathVariable Long marksId,
                                                     @Valid @RequestBody MarksRequest request){
        return ResponseEntity.ok(marksService.updateMarks(marksId, request));
    }

    @DeleteMapping("/{marksId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Void> delete(@PathVariable Long marksId){
        marksService.delete(marksId);
        return ResponseEntity.noContent().build();
    }
}
