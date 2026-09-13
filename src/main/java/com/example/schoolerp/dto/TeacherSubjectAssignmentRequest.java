package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotNull;

public class TeacherSubjectAssignmentRequest {

    @NotNull
    private Long teacherId;

    @NotNull
    private Long sectionSubjectId;

    public TeacherSubjectAssignmentRequest() {
    }

    public TeacherSubjectAssignmentRequest(Long teacherId, Long sectionSubjectId) {
        this.teacherId = teacherId;
        this.sectionSubjectId = sectionSubjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSectionSubjectId() {
        return sectionSubjectId;
    }

    public void setSectionSubjectId(Long sectionSubjectId) {
        this.sectionSubjectId = sectionSubjectId;
    }
}
