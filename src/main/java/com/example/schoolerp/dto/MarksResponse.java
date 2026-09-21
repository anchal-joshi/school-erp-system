package com.example.schoolerp.dto;

public class MarksResponse {

    private Long id;
    private Long studentId;
    private Long subjectId;
    private Long examId;
    private Double marks;
    private Double fullMarks;

    public MarksResponse() {
    }

    public MarksResponse(Long id, Long studentId, Long subjectId, Long examId, Double marks, Double fullMarks) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.examId = examId;
        this.marks = marks;
        this.fullMarks = fullMarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
