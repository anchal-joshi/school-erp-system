package com.example.schoolerp.service;

import com.example.schoolerp.dto.*;
import com.example.schoolerp.entity.Teacher;
import com.example.schoolerp.entity.TeacherSubjectAssignment;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.exception.TeacherNotFoundException;
import com.example.schoolerp.repository.*;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherDashboardService {

    private final CurrentUserService currentUserService;
    private final TeacherRepository teacherRepository;
    private final TeacherSubjectAssignmentRepository teacherSubjectAssignmentRepository;


    public TeacherDashboardService(CurrentUserService currentUserService, TeacherRepository teacherRepository, TeacherSubjectAssignmentRepository teacherSubjectAssignmentRepository) {
        this.currentUserService = currentUserService;
        this.teacherRepository = teacherRepository;
        this.teacherSubjectAssignmentRepository = teacherSubjectAssignmentRepository;
    }

    public TeacherDashboardResponse dashboard(){

        User user = currentUserService.getCurrentUser();

        System.out.println("Current user id: " + user.getId());
        System.out.println("Current user email: " + user.getEmail());

        Teacher teacher = teacherRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));

        TeacherResponse profile = new TeacherResponse(
                teacher.getId(),
                teacher.getUser().getId(),
                teacher.getUser().getEmail(),
                teacher.getName(),
                teacher.getPhone()
        );

        List<TeacherSubjectAssignment> assignments = teacherSubjectAssignmentRepository.findByTeacher_Id(teacher.getId());

        List<TeacherSubjectAssignmentDashboardResponse> assignmentResponses = assignments.stream()
                .map(assignment -> new TeacherSubjectAssignmentDashboardResponse(
                        assignment.getId(),

                        assignment.getSectionSubject().getSection().getId(),
                        assignment.getSectionSubject().getSection().getName(),

                        assignment.getSectionSubject().getSubject().getId(),
                        assignment.getSectionSubject().getSubject().getName()
                )).toList();


        return new TeacherDashboardResponse(
                profile,
                assignmentResponses
        );
    }
}
