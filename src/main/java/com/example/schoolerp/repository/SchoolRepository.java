package com.example.schoolerp.repository;

import com.example.schoolerp.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
