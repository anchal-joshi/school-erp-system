package com.example.schoolerp.repository;

import com.example.schoolerp.entity.SectionSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionSubjectRepository extends JpaRepository<SectionSubject, Long> {

    List<SectionSubject> findAllBySchool_Id(Long schoolId);

    List<SectionSubject> findAllBySchool_IdAndSection_Id(Long schoolId,
                                                         Long sectionId);
}
