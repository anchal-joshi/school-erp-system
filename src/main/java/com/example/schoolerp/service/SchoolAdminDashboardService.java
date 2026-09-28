package com.example.schoolerp.service;

import com.example.schoolerp.dto.OverviewResponse;
import com.example.schoolerp.dto.SchoolAdminDashboardResponse;
import com.example.schoolerp.dto.SchoolResponse;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.entity.Class;
import com.example.schoolerp.exception.SchoolNotFoundException;
import com.example.schoolerp.repository.*;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolAdminDashboardService {

    private CurrentUserService currentUserService;
    private SchoolRepository schoolRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private ClassRepository classRepository;
    private SectionRepository sectionRepository;
    private SubjectRepository subjectRepository;

    public SchoolAdminDashboardService(CurrentUserService currentUserService, SchoolRepository schoolRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ClassRepository classRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository) {
        this.currentUserService = currentUserService;
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
    }

    public SchoolAdminDashboardResponse dashboard(){

        School school = schoolRepository.findById(currentUserService.getCurrentSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("School not found"));

        SchoolResponse schoolProfile = new SchoolResponse(
                school.getId(),
                school.getName(),
                school.getAddress(),
                school.getContactEmail(),
                school.getContactPhone(),
                school.getStatus(),
                school.getCreatedAt()
        );

        List<Student> students = studentRepository.findByUser_School_Id(school.getId());
        List<Teacher> teachers = teacherRepository.findByUser_School_Id(school.getId());
        List<Class> classes = classRepository.findBySchool_Id(school.getId());
        List<Section> sections = sectionRepository.findBySchool_Id(school.getId());
        List<Subject> subjects = subjectRepository.findBySchool_Id(school.getId());

        OverviewResponse overview = new OverviewResponse(
                students.size(),
                teachers.size(),
                classes.size(),
                sections.size(),
                subjects.size()
        );

        return new SchoolAdminDashboardResponse(
                schoolProfile,
                overview
        );
    }
}
