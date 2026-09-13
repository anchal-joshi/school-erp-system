package com.example.schoolerp.dto;

public class TeacherSubjectAssignmentResponse {

    private Long id;
    private Long teacherId;
    private Long sectionSubjectId;
    private Long schoolId;

    public TeacherSubjectAssignmentResponse() {
    }

    public TeacherSubjectAssignmentResponse(Long id, Long teacherId, Long sectionSubjectId, Long schoolId) {
        this.id = id;
        this.teacherId = teacherId;
        this.sectionSubjectId = sectionSubjectId;
        this.schoolId = schoolId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
