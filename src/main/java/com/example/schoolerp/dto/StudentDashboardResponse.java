package com.example.schoolerp.dto;

import java.util.List;

public class StudentDashboardResponse {

    private StudentResponse profile;
    private List<SubjectResponse> subjects;
    private AttendanceSummaryResponse attendance;
    private List<ExamResultResponse> marks;

    public StudentDashboardResponse() {
    }

    public StudentDashboardResponse(StudentResponse profile, List<SubjectResponse> subjects, AttendanceSummaryResponse attendance, List<ExamResultResponse> marks) {
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

    public AttendanceSummaryResponse getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceSummaryResponse attendance) {
        this.attendance = attendance;
    }

    public List<ExamResultResponse> getMarks() {
        return marks;
    }

    public void setMarks(List<ExamResultResponse> marks) {
        this.marks = marks;
    }
}
