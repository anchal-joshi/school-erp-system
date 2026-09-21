package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotNull;

public class MarksRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long examId;

    @NotNull
    private Double marks;

    @NotNull
    private Double fullMarks;

    public MarksRequest() {
    }

    public MarksRequest(Long studentId, Long subjectId, Long examId, Double marks, Double fullMarks) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.examId = examId;
        this.marks = marks;
        this.fullMarks = fullMarks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
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
