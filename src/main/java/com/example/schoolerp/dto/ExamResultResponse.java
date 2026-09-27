package com.example.schoolerp.dto;

import java.time.Year;
import java.util.List;

public class ExamResultResponse {

    private Long examId;
    private String examName;
    private Year year;
    private List<SubjectMarksResponse> subjectsMarks;

    public ExamResultResponse() {
    }

    public ExamResultResponse(Long examId, String examName, Year year, List<SubjectMarksResponse> subjectsMarks) {
        this.examId = examId;
        this.examName = examName;
        this.year = year;
        this.subjectsMarks = subjectsMarks;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<SubjectMarksResponse> getSubjectsMarks() {
        return subjectsMarks;
    }

    public void setSubjectsMarks(List<SubjectMarksResponse> subjectsMarks) {
        this.subjectsMarks = subjectsMarks;
    }
}
