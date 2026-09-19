package com.example.schoolerp.dto;

import com.example.schoolerp.entity.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AttendanceRequest {


    @NotNull
    private Long studentId;

    @NotNull
    private LocalDate date;

    @NotNull
    private AttendanceStatus status;

    public AttendanceRequest() {
    }

    public AttendanceRequest(Long studentId, LocalDate date, AttendanceStatus status) {
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
