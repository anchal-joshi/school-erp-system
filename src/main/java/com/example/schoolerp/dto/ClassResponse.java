package com.example.schoolerp.dto;

public class ClassResponse {

    private Long id;
    private String name;
    private Long schoolId;

    public ClassResponse() {
    }

    public ClassResponse(Long id, String name, Long schoolId) {
        this.id = id;
        this.name = name;
        this.schoolId = schoolId;
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

}
