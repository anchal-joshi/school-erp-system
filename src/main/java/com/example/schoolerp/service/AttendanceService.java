package com.example.schoolerp.service;

import com.example.schoolerp.dto.AttendanceRequest;
import com.example.schoolerp.dto.AttendanceResponse;
import com.example.schoolerp.entity.Attendance;
import com.example.schoolerp.entity.Student;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.exception.AttendanceAlreadyExistsException;
import com.example.schoolerp.exception.AttendanceNotFoundException;
import com.example.schoolerp.exception.StudentNotFoundException;
import com.example.schoolerp.repository.AttendanceRepository;
import com.example.schoolerp.repository.StudentRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CurrentUserService currentUserService;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository, CurrentUserService currentUserService) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.currentUserService = currentUserService;
    }

    public AttendanceResponse createAttendance(AttendanceRequest request){

        User user = currentUserService.getCurrentUser();

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(()-> new StudentNotFoundException("Student not found with id: "+ request.getStudentId()));

        if (!user.getSchool().getId().equals(student.getUser().getSchool().getId())){
            throw new IllegalArgumentException("You can only create attendance for students of your own school");
        }


        if (attendanceRepository.existsByStudentIdAndDate(request.getStudentId(), request.getDate())){
            throw new AttendanceAlreadyExistsException("Attendance already exists for this student");
        }

        Attendance attendance = new Attendance();

        attendance.setStudent(student);
        attendance.setDate(request.getDate());
        attendance.setStatus(request.getStatus());

        Attendance saved = attendanceRepository.save(attendance);

        AttendanceResponse response = new AttendanceResponse(
                saved.getStudent().getId(),
                saved.getDate(),
                saved.getStatus()
        );

        return response;
    }

    public AttendanceResponse getAttendance(Long studentId, LocalDate date){

        User user = currentUserService.getCurrentUser();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ studentId));

        if (!user.getSchool().getId().equals(student.getUser().getSchool().getId())){
            throw new IllegalArgumentException("You can only get attendance for students of your own school");
        }

        Attendance attendance = attendanceRepository
                .findByStudentIdAndDate(studentId, date)
                .orElseThrow(() -> new AttendanceNotFoundException("Attendance not found for student with id: "+ studentId +" + on date: "+ date));

        AttendanceResponse response = new AttendanceResponse();

        response.setStudentId(attendance.getStudent().getId());
        response.setDate(attendance.getDate());
        response.setStatus(attendance.getStatus());

        return response;
    }

    public AttendanceResponse updateAttendance(Long studentId, AttendanceRequest request){

        User user = currentUserService.getCurrentUser();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ studentId));

        if (!user.getSchool().getId().equals(student.getUser().getSchool().getId())){
            throw new IllegalArgumentException("You can only create attendance for students of your own school");
        }

        Attendance attendance = attendanceRepository
                .findByStudentIdAndDate(studentId, request.getDate())
                .orElseThrow(() -> new AttendanceNotFoundException("Attendance not found for student with id: "+ studentId +" + on date: "+ request.getDate()));

        attendance.setStatus(request.getStatus());

        Attendance saved = attendanceRepository.save(attendance);

        AttendanceResponse response = new AttendanceResponse();

        response.setStudentId(saved.getStudent().getId());
        response.setDate(saved.getDate());
        response.setStatus(saved.getStatus());

        return response;
    }
}
