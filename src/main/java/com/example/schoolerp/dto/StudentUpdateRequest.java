package com.example.schoolerp.dto;

public class StudentUpdateRequest {
    private String name;
    private String phone;
    private Long sectionId;

    public StudentUpdateRequest() {
    }

    public StudentUpdateRequest(String name, String phone, Long sectionId) {
        this.name = name;
        this.phone = phone;
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
}
