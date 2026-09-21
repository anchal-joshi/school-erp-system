package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    boolean existsByStudentIdAndSubjectIdAndExamId(Long studentId, Long subjectId, Long examId);
}
