package com.example.schoolerp.dto;

import com.example.schoolerp.entity.SchoolStatus;

import java.time.LocalDateTime;

public class SchoolResponse {

    private Long id;
    private String name;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private SchoolStatus status;
    private LocalDateTime createdAt;

    public SchoolResponse() {
    }

    public SchoolResponse(Long id, String name, String address, String contactEmail, String contactPhone, SchoolStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SchoolStatus getStatus() {
        return status;
    }

    public void setStatus(SchoolStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
