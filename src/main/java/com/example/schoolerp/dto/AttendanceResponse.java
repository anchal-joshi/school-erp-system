package com.example.schoolerp.dto;

import com.example.schoolerp.entity.AttendanceStatus;

import java.time.LocalDate;

public class AttendanceResponse {


    private Long studentId;
    private LocalDate date;
    private AttendanceStatus status;

    public AttendanceResponse() {
    }

    public AttendanceResponse(Long studentId, LocalDate date, AttendanceStatus status) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
