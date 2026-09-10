package com.example.schoolerp.repository;

import com.example.schoolerp.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findBySchool_Id(Long schoolId);

    List<Section> findBySchool_IdAndAClass_Id(Long schoolId, Long classId);
}
