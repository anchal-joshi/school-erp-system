package com.example.schoolerp.dto;

public class SchoolAdminDashboardResponse {

    private SchoolResponse profile;
    private OverviewResponse overviewResponse;

    public SchoolAdminDashboardResponse() {
    }

    public SchoolAdminDashboardResponse(SchoolResponse profile, OverviewResponse overviewResponse) {
        this.profile = profile;
        this.overviewResponse = overviewResponse;
    }

    public SchoolResponse getProfile() {
        return profile;
    }

    public void setProfile(SchoolResponse profile) {
        this.profile = profile;
    }

    public OverviewResponse getOverviewResponse() {
        return overviewResponse;
    }

    public void setOverviewResponse(OverviewResponse overviewResponse) {
        this.overviewResponse = overviewResponse;
    }
}
