package com.example.schoolerp.dto;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class StudentDashboardResponse {

    private StudentResponse profile;
    private List<SubjectResponse> subjects;
    private AttendanceResponse attendance;
    private MarksResponse marks;

    public StudentDashboardResponse() {
    }

    public StudentDashboardResponse(StudentResponse profile, List<SubjectResponse> subjects, AttendanceResponse attendance, MarksResponse marks) {
        this.profile = profile;
        this.subjects = subjects;
        this.attendance = attendance;
        this.marks = marks;
    }

    public StudentResponse getProfile() {
        return profile;
    }

    public void setProfile(StudentResponse profile) {
        this.profile = profile;
    }

    public List<SubjectResponse> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectResponse> subjects) {
        this.subjects = subjects;
    }

    public AttendanceResponse getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceResponse attendance) {
        this.attendance = attendance;
    }

    public MarksResponse getMarks() {
        return marks;
    }

    public void setMarks(MarksResponse marks) {
        this.marks = marks;
    }
}
