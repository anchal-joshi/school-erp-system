package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {

    List<Class> findBySchool_Id(Long schoolId);
}
