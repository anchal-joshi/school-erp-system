package com.example.schoolerp.dto;

import com.example.schoolerp.entity.School;
import com.example.schoolerp.entity.UserRole;
import com.example.schoolerp.entity.UserStatus;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String email;
    private UserRole role;
    private UserStatus status;
    private Long schoolId;
    private LocalDateTime createdAt;

    public UserResponse() {
    }

    public UserResponse(Long id, String email, UserRole role, UserStatus status, Long schoolId, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.status = status;
        this.schoolId = schoolId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
