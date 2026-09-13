package com.example.schoolerp.service;

import com.example.schoolerp.dto.TeacherSubjectAssignmentRequest;
import com.example.schoolerp.dto.TeacherSubjectAssignmentResponse;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.SchoolNotFoundException;
import com.example.schoolerp.exception.SectionSubjectNotFoundException;
import com.example.schoolerp.exception.TeacherNotFoundException;
import com.example.schoolerp.exception.TeacherSubjectAssignmentNotFoundException;
import com.example.schoolerp.repository.SchoolRepository;
import com.example.schoolerp.repository.SectionSubjectRepository;
import com.example.schoolerp.repository.TeacherRepository;
import com.example.schoolerp.repository.TeacherSubjectAssignmentRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSubjectAssignmentService {

    private final TeacherSubjectAssignmentRepository repository;
    private final CurrentUserService currentUserService;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;
    private final SectionSubjectRepository sectionSubjectRepository;

    public TeacherSubjectAssignmentService(TeacherSubjectAssignmentRepository repository, CurrentUserService currentUserService, SchoolRepository schoolRepository, TeacherRepository teacherRepository, SectionSubjectRepository sectionSubjectRepository) {
        this.repository = repository;
        this.currentUserService = currentUserService;
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
        this.sectionSubjectRepository = sectionSubjectRepository;
    }

    public TeacherSubjectAssignmentResponse create(TeacherSubjectAssignmentRequest request){

        User currentUser = currentUserService.getCurrentUser();

        School school = schoolRepository.findById(currentUser.getSchool().getId())
                .orElseThrow(() -> new SchoolNotFoundException("School not found with id: "+ currentUser.getSchool().getId()));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException("Teach not found with id: "+ request.getTeacherId()));

        SectionSubject sectionSubject = sectionSubjectRepository.findById(request.getSectionSubjectId())
                .orElseThrow(() -> new SectionSubjectNotFoundException("SectionSubject not found with id: "+ request.getSectionSubjectId()));

        if (!teacher.getUser().getSchool().getId().equals(school.getId())){
            throw new IllegalArgumentException("Wrong School");
        }

        if (!sectionSubject.getSchool().getId().equals(school.getId())){
            throw new IllegalArgumentException("Wrong School");
        }

        TeacherSubjectAssignment assignment = new TeacherSubjectAssignment();

        assignment.setTeacher(teacher);
        assignment.setSchool(school);
        assignment.setSectionSubject(sectionSubject);

        TeacherSubjectAssignment saved = repository.save(assignment);

        return new TeacherSubjectAssignmentResponse(
                saved.getId(),
                saved.getTeacher().getId(),
                saved.getSectionSubject().getId(),
                saved.getSchool().getId()
        );
    }

    public List<TeacherSubjectAssignmentResponse> getAll(){

        User currentUser = currentUserService.getCurrentUser();

        List<TeacherSubjectAssignment> assignments = repository.findAllBySchool_Id(currentUser.getSchool().getId());

        List<TeacherSubjectAssignmentResponse> responses = assignments
                .stream()
                .map(assignment -> new TeacherSubjectAssignmentResponse(
                        assignment.getId(),
                        assignment.getTeacher().getId(),
                        assignment.getSectionSubject().getId(),
                        assignment.getSchool().getId()
                )).toList();
        return responses;
    }

    public TeacherSubjectAssignmentResponse getById(Long id){

        User currentUser = currentUserService.getCurrentUser();

        TeacherSubjectAssignment assignment = repository.findById(id)
                .orElseThrow(() -> new TeacherSubjectAssignmentNotFoundException("Not found with id: "+ id));

        if (!currentUser.getSchool().getId().equals(assignment.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school");
        }

        return new TeacherSubjectAssignmentResponse(
                assignment.getId(),
                assignment.getTeacher().getId(),
                assignment.getSectionSubject().getId(),
                assignment.getSchool().getId()
        );
    }

    public void delete(Long id){
        User currentUser = currentUserService.getCurrentUser();

        TeacherSubjectAssignment assignment = repository.findById(id)
                .orElseThrow(() -> new TeacherSubjectAssignmentNotFoundException("Not found with id: "+ id));

        if (!currentUser.getSchool().getId().equals(assignment.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school");
        }

        repository.deleteById(id);
    }
}
