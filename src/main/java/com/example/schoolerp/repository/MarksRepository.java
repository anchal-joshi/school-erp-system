package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findAllByStudentId(Long studentId);

    boolean existsByStudentIdAndSubjectIdAndExamId(Long studentId, Long subjectId, Long examId);
}
