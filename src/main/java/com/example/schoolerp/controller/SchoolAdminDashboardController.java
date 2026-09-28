package com.example.schoolerp.controller;

import com.example.schoolerp.dto.SchoolAdminDashboardResponse;
import com.example.schoolerp.service.SchoolAdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schooladmin")
public class SchoolAdminDashboardController {

    private final SchoolAdminDashboardService schoolAdminDashboardService;

    public SchoolAdminDashboardController(SchoolAdminDashboardService schoolAdminDashboardService) {
        this.schoolAdminDashboardService = schoolAdminDashboardService;
    }

    @GetMapping
    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    public ResponseEntity<SchoolAdminDashboardResponse> dashboard(){
        return ResponseEntity.ok(schoolAdminDashboardService.dashboard());
    }
}
