package com.example.schoolerp.service;

import com.example.schoolerp.dto.StudentRequest;
import com.example.schoolerp.dto.StudentSubjectEnrollmentRequest;
import com.example.schoolerp.dto.StudentSubjectEnrollmentResponse;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.SchoolNotFoundException;
import com.example.schoolerp.exception.SectionSubjectNotFoundException;
import com.example.schoolerp.exception.StudentNotFoundException;
import com.example.schoolerp.exception.StudentSubjectEnrollmentNotFoundException;
import com.example.schoolerp.repository.SchoolRepository;
import com.example.schoolerp.repository.SectionSubjectRepository;
import com.example.schoolerp.repository.StudentRepository;
import com.example.schoolerp.repository.StudentSubjectEnrollmentRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSubjectEnrollmentService {

    private final StudentSubjectEnrollmentRepository repository;

    private final CurrentUserService currentUserService;
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final SectionSubjectRepository sectionSubjectRepository;

    public StudentSubjectEnrollmentService(StudentSubjectEnrollmentRepository repository, CurrentUserService currentUserService, SchoolRepository schoolRepository, StudentRepository studentRepository, SectionSubjectRepository sectionSubjectRepository) {

        this.repository = repository;
        this.currentUserService = currentUserService;
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.sectionSubjectRepository = sectionSubjectRepository;
    }

    public StudentSubjectEnrollmentResponse create(StudentSubjectEnrollmentRequest request){

        User currentUser = currentUserService.getCurrentUser();

        School school = schoolRepository.findById(currentUser.getSchool().getId())
                .orElseThrow(() -> new SchoolNotFoundException("School not found with id: "+ currentUser.getSchool().getId()));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ request.getStudentId()));

        SectionSubject sectionSubject = sectionSubjectRepository.findById(request.getSectionSubjectId())
                .orElseThrow(() -> new SectionSubjectNotFoundException("SectionSubject not found with id: "+ request.getSectionSubjectId()));

        if (!school.getId().equals(currentUser.getSchool().getId())){
            throw new IllegalArgumentException("Wrong School");
        }

        if (!sectionSubject.getSchool().getId().equals(currentUser.getSchool().getId())){
            throw new IllegalArgumentException("Wrong School");
        }

        if (!student.getUser().getSchool().getId().equals(school.getId())){
            throw new IllegalArgumentException("Wrong School");
        }

        if (!student.getSection().equals(sectionSubject.getSection())){
            throw new IllegalArgumentException("Student's section and SectionSubject's section donot match");
        }

        StudentSubjectEnrollment enrollment = new StudentSubjectEnrollment();

        enrollment.setSchool(school);
        enrollment.setSectionSubject(sectionSubject);
        enrollment.setStudent(student);

        StudentSubjectEnrollment saved = repository.save(enrollment);

        return new StudentSubjectEnrollmentResponse(
                saved.getId(),
                saved.getSectionSubject().getId()
        );
    }

    //GET all, Get all by Id, Delete

    public List<StudentSubjectEnrollmentResponse> getAll(){

        Long currentSchoolId = currentUserService.getCurrentSchoolId();

        List<StudentSubjectEnrollment> enrollments = repository.findAllBySchool_Id(currentSchoolId);

        List<StudentSubjectEnrollmentResponse> responses = enrollments
                .stream()
                .map(enrollment -> new StudentSubjectEnrollmentResponse(
                        enrollment.getId(),
                        enrollment.getSectionSubject().getId()
                )).toList();

        return responses;
    }

    public StudentSubjectEnrollmentResponse getById(Long id){

        User currentUser = currentUserService.getCurrentUser();

        StudentSubjectEnrollment enrollment = repository.findById(id)
                .orElseThrow(()-> new StudentSubjectEnrollmentNotFoundException("StudentSubjectEnrollment not found with id: "+ id));

        if (!currentUser.getSchool().getId().equals(enrollment.getSchool().getId())){
            throw new IllegalArgumentException("Wrong School");
        }

        return new StudentSubjectEnrollmentResponse(
                enrollment.getId(),
                enrollment.getSectionSubject().getId()
        );
    }

    public void delete(Long id){
        User currentUser = currentUserService.getCurrentUser();

        StudentSubjectEnrollment enrollment = repository.findById(id)
                .orElseThrow(()-> new StudentSubjectEnrollmentNotFoundException("StudentSubjectEnrollment not found with id: "+ id));

        if (!currentUser.getSchool().getId().equals(enrollment.getSchool().getId())){
            throw new IllegalArgumentException("WRong School");
        }

        repository.deleteById(id);
    }

}
