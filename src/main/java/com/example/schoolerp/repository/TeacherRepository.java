package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByUser_School_Id(Long id);

    Optional<Teacher> findByUser_Id(Long id);
}
