package com.example.schoolerp.controller;

import com.example.schoolerp.dto.AttendanceRequest;
import com.example.schoolerp.dto.AttendanceResponse;
import com.example.schoolerp.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AttendanceResponse> create(@RequestBody AttendanceRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.createAttendance(request));
    }

    @GetMapping("/{studentId}/{date}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AttendanceResponse> getAttendance(@PathVariable Long studentId, @PathVariable LocalDate date){
        return ResponseEntity.ok(attendanceService.getAttendance(studentId, date));
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AttendanceResponse> update(@PathVariable Long studentId, @RequestBody AttendanceRequest request){
        return ResponseEntity.ok(attendanceService.updateAttendance(studentId, request));
    }

}
