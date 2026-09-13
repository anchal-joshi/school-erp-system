package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByUser_School_Id(Long schoolId);

    Optional<Student> findByUser_Id(Long userId);
}
