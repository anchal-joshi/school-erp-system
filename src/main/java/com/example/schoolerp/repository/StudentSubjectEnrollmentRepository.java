package com.example.schoolerp.repository;

import com.example.schoolerp.entity.StudentSubjectEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentSubjectEnrollmentRepository extends JpaRepository<StudentSubjectEnrollment, Long> {

    List<StudentSubjectEnrollment> findAllBySchool_Id(Long schoolId);
}
