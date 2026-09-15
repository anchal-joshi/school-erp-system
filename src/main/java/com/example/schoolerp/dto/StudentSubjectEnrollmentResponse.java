package com.example.schoolerp.dto;

public class StudentSubjectEnrollmentResponse {

    private Long id;
    private Long sectionSubjectId;

    public StudentSubjectEnrollmentResponse() {
    }

    public StudentSubjectEnrollmentResponse(Long id, Long sectionSubjectId) {
        this.id = id;
        this.sectionSubjectId = sectionSubjectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSectionSubjectId() {
        return sectionSubjectId;
    }

    public void setSectionSubjectId(Long sectionSubjectId) {
        this.sectionSubjectId = sectionSubjectId;
    }
}
