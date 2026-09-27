package com.example.schoolerp.dto;

public class SubjectMarksResponse {

    private Long subjectId;
    private String subjectName;
    private Double marks;
    private Double fullMarks;

    public SubjectMarksResponse() {
    }

    public SubjectMarksResponse(Long subjectId, String subjectName, Double marks, Double fullMarks) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.marks = marks;
        this.fullMarks = fullMarks;
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

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public Double getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(Double fullMarks) {
        this.fullMarks = fullMarks;
    }
}
