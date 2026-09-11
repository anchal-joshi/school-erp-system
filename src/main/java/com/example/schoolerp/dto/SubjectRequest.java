package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotBlank;

public class SubjectRequest {

    @NotBlank
    private String name;

    public SubjectRequest() {
    }

    public SubjectRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
