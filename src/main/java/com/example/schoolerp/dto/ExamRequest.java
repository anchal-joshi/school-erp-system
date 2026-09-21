package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public class ExamRequest {

    @NotBlank
    private String name;

    @NotNull
    private Year year;

    public ExamRequest() {
    }

    public ExamRequest(String name, Year year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
