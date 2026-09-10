package com.example.schoolerp.dto;

public class SectionResponse {

    private Long id;
    private String name;
    private Long classId;
    private Long schoolId;

    public SectionResponse() {
    }

    public SectionResponse(Long id, String name, Long classId, Long schoolId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
