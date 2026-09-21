package com.example.schoolerp.dto;

import java.time.Year;

public class ExamResponse {

    private Long id;
    private String name;
    private Year year;

    public ExamResponse() {
    }

    public ExamResponse(Long id, String name, Year year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
