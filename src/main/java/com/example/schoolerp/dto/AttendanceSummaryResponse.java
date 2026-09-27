package com.example.schoolerp.dto;

public class AttendanceSummaryResponse {

    private Integer totalDays;
    private Integer presentDays;
    private Integer absentDays;

    public AttendanceSummaryResponse() {
    }

    public AttendanceSummaryResponse(Integer totalDays, Integer presentDays, Integer absentDays) {
        this.totalDays = totalDays;
        this.presentDays = presentDays;
        this.absentDays = absentDays;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(Integer presentDays) {
        this.presentDays = presentDays;
    }

    public Integer getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(Integer absentDays) {
        this.absentDays = absentDays;
    }
}
