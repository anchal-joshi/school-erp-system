package com.example.schoolerp.service;

import com.example.schoolerp.dto.ChangePasswordRequest;
import com.example.schoolerp.dto.StudentRequest;
import com.example.schoolerp.dto.StudentResponse;
import com.example.schoolerp.dto.StudentUpdateRequest;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.SectionNotFoundException;
import com.example.schoolerp.exception.StudentNotFoundException;
import com.example.schoolerp.exception.UserNotFoundException;
import com.example.schoolerp.repository.SectionRepository;
import com.example.schoolerp.repository.StudentRepository;
import com.example.schoolerp.repository.UserRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final SectionRepository sectionRepository;
    private final CurrentUserService currentUserService;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository, SectionRepository sectionRepository, CurrentUserService currentUserService, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.currentUserService = currentUserService;
        this.passwordEncoder = passwordEncoder;
    }

    public StudentResponse createStudent(StudentRequest request){

        User currentUser = currentUserService.getCurrentUser();

        User existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser != null){
            throw new IllegalArgumentException("Email already exists");
        }

        Student student = new Student();

        Section section = sectionRepository.findById(request.getSectionId())
                        .orElseThrow(() -> new SectionNotFoundException("Section not found with id: "+ request.getSectionId()));

        School school = section.getSchool();

        Long schoolId = school.getId();

        if (!currentUser.getSchool().getId().equals(schoolId)){
            throw new IllegalArgumentException("You can only create students for your own school");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(UserRole.STUDENT);
        user.setSchool(school);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        student.setName(request.getName());
        student.setSection(section);
        student.setPhone(request.getPhone());
        student.setUser(savedUser);

        Student savedStudent = studentRepository.save(student);

        return new StudentResponse(
                savedStudent.getId(),
                savedStudent.getUser().getId(),
                request.getEmail(),
                savedStudent.getName(),
                savedStudent.getPhone(),
                savedStudent.getSection().getId()
        );
    }

    public List<StudentResponse> getAllStudents() {

        Long schoolId = currentUserService.getCurrentSchoolId();

        List<Student> students = studentRepository.findByUser_School_Id(schoolId);

        List<StudentResponse> responses = students.stream()
                .map(student -> new StudentResponse(
                        student.getId(),
                        student.getUser().getId(),
                        student.getUser().getEmail(),
                        student.getName(),
                        student.getPhone(),
                        student.getSection().getId()
                )).toList();
        return responses;
    }

    public StudentResponse getStudentById(Long id){

        Long studentId = currentUserService.getCurrentSchoolId();

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ id));

        if (!student.getUser().getSchool().getId().equals(studentId)){
            throw new IllegalArgumentException("You can only access students from your own school");
        }

        return new StudentResponse(
                student.getId(),
                student.getUser().getId(),
                student.getUser().getEmail(),
                student.getName(),
                student.getPhone(),
                student.getSection().getId()
        );
    }

    public void deleteStudentById(Long id){

        Long studentId = currentUserService.getCurrentSchoolId();

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ id));

        if (!student.getUser().getSchool().getId().equals(studentId)){
            throw new IllegalArgumentException("You can only access students from your own school");
        }

        studentRepository.deleteById(id);
        userRepository.delete(student.getUser());
    }

    public StudentResponse updateStudent(Long id, StudentUpdateRequest request){

        Long studentId = currentUserService.getCurrentSchoolId();

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ id));

        if (!student.getUser().getSchool().getId().equals(studentId)){
            throw new IllegalArgumentException("You can only access students from your own school");
        }

        Section section = sectionRepository.findById(request.getSectionId())
                .orElseThrow(() ->
                        new SectionNotFoundException(
                                "Section not found with id: " + request.getSectionId()));

        if (!section.getSchool().getId().equals(studentId)){
            throw new IllegalArgumentException(
                    "You can only assign students to sections from your own school");
        }

        student.setName(request.getName());
        student.setPhone(request.getPhone());
        student.setSection(section);

        Student savedStudent = studentRepository.save(student);

        return new StudentResponse(
                savedStudent.getId(),
                student.getUser().getId(),
                student.getUser().getEmail(),
                student.getName(),
                student.getPhone(),
                student.getSection().getId()
        );
    }

    public String changePassword(ChangePasswordRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Student student = studentRepository.findByUser_Id(currentUser.getId())
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student profile not found"));

        if (!passwordEncoder.matches(
                request.getOldPassword(),
                currentUser.getPassword())){

            throw new IllegalArgumentException(
                    "Your current password which you entered is wrong");
        }

        currentUser.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(currentUser);

        return "Password changed successfully!";
    }
}
