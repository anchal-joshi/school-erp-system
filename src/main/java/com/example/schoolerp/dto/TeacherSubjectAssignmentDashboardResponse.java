package com.example.schoolerp.dto;

public class TeacherSubjectAssignmentDashboardResponse {
    private Long assignmentId;
    private Long sectionId;
    private String sectionName;
    private Long subjectId;
    private String subjectName;

    public TeacherSubjectAssignmentDashboardResponse(Long assignmentId, Long sectionId, String sectionName, Long subjectId, String subjectName) {
        this.assignmentId = assignmentId;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public TeacherSubjectAssignmentDashboardResponse() {
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
