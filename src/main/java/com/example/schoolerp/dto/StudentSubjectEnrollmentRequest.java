package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotNull;

public class StudentSubjectEnrollmentRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long sectionSubjectId;

    public StudentSubjectEnrollmentRequest() {
    }

    public StudentSubjectEnrollmentRequest(Long studentId, Long sectionSubjectId) {
        this.studentId = studentId;
        this.sectionSubjectId = sectionSubjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSectionSubjectId() {
        return sectionSubjectId;
    }

    public void setSectionSubjectId(Long sectionSubjectId) {
        this.sectionSubjectId = sectionSubjectId;
    }
}
