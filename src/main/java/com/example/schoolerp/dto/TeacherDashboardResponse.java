package com.example.schoolerp.dto;

import java.util.List;

public class TeacherDashboardResponse {

    private TeacherResponse profile;
    private List<TeacherSubjectAssignmentDashboardResponse> assignments;


    public TeacherDashboardResponse(TeacherResponse profile, List<TeacherSubjectAssignmentDashboardResponse> assignments) {
        this.profile = profile;
        this.assignments = assignments;
    }

    public TeacherResponse getProfile() {
        return profile;
    }

    public void setProfile(TeacherResponse profile) {
        this.profile = profile;
    }

    public List<TeacherSubjectAssignmentDashboardResponse> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<TeacherSubjectAssignmentDashboardResponse> assignments) {
        this.assignments = assignments;
    }
}
