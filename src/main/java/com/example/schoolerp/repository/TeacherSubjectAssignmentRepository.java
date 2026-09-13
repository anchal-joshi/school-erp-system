package com.example.schoolerp.repository;

import com.example.schoolerp.entity.TeacherSubjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherSubjectAssignmentRepository extends JpaRepository<TeacherSubjectAssignment, Long> {

    List<TeacherSubjectAssignment> findAllBySchool_Id(Long id);
}
