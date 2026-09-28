package com.example.schoolerp.dto;

public class OverviewResponse {

    private Integer totalStudents;
    private Integer totalTeachers;
    private Integer totalClasses;
    private Integer totalSections;
    private Integer totalSubjects;

    public OverviewResponse() {
    }

    public OverviewResponse(Integer totalStudents, Integer totalTeachers, Integer totalClasses, Integer totalSections, Integer totalSubjects) {
        this.totalStudents = totalStudents;
        this.totalTeachers = totalTeachers;
        this.totalClasses = totalClasses;
        this.totalSections = totalSections;
        this.totalSubjects = totalSubjects;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getTotalTeachers() {
        return totalTeachers;
    }

    public void setTotalTeachers(Integer totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public Integer getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(Integer totalClasses) {
        this.totalClasses = totalClasses;
    }

    public Integer getTotalSections() {
        return totalSections;
    }

    public void setTotalSections(Integer totalSections) {
        this.totalSections = totalSections;
    }

    public Integer getTotalSubjects() {
        return totalSubjects;
    }

    public void setTotalSubjects(Integer totalSubjects) {
        this.totalSubjects = totalSubjects;
    }
}
