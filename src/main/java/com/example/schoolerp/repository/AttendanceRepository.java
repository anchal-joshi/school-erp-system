package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Boolean existsByStudentIdAndDate(Long studentId, LocalDate date);

    List<Attendance> findAllByStudentId(Long studentId);

    Optional<Attendance> findByStudentIdAndDate(Long studentId, LocalDate date);
}
