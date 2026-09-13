package com.example.schoolerp.service;

import com.example.schoolerp.dto.ChangePasswordRequest;
import com.example.schoolerp.dto.TeacherRequest;
import com.example.schoolerp.dto.TeacherResponse;
import com.example.schoolerp.dto.TeacherUpdateRequest;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.TeacherNotFoundException;
import com.example.schoolerp.repository.SchoolRepository;
import com.example.schoolerp.repository.TeacherRepository;
import com.example.schoolerp.repository.UserRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final PasswordEncoder passwordEncoder;
    private final SchoolRepository schoolRepository;

    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository, CurrentUserService currentUserService, PasswordEncoder passwordEncoder, SchoolRepository schoolRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.passwordEncoder = passwordEncoder;
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public TeacherResponse createTeacher(TeacherRequest request){
        User user = currentUserService.getCurrentUser();

        School school = user.getSchool();

        User existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser != null){
            throw new IllegalArgumentException("Email already exists");
        }

        User createdUser = new User();
        createdUser.setRole(UserRole.TEACHER);
        createdUser.setSchool(school);
        createdUser.setEmail(request.getEmail());
        createdUser.setPassword(passwordEncoder.encode(request.getPassword()));
        createdUser.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(createdUser);

        Teacher teacher = new Teacher();
        teacher.setName(request.getName());
        teacher.setPhone(request.getPhone());
        teacher.setUser(savedUser);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return new TeacherResponse(
                savedTeacher.getId(),
                savedTeacher.getUser().getId(),
                savedUser.getEmail(),
                savedTeacher.getName(),
                savedTeacher.getPhone()
        );
    }

    public List<TeacherResponse> getAllTeachers(){

        User currentUser = currentUserService.getCurrentUser();

        List<Teacher> teachers = teacherRepository.findByUser_School_Id(currentUser.getSchool().getId());

        List<TeacherResponse> responses = teachers
                .stream()
                .map(teacher -> new TeacherResponse(
                        teacher.getId(),
                        teacher.getUser().getId(),
                        teacher.getUser().getEmail(),
                        teacher.getName(),
                        teacher.getPhone()
                )).toList();

        return responses;
    }


    public TeacherResponse getTeacherById(Long id){

        Long schoolId = currentUserService.getCurrentSchoolId();

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: "+ id));

        if (!schoolId.equals(teacher.getUser().getSchool().getId())){
            throw new IllegalArgumentException("You can only access teachers of your own school");
        }

        return new TeacherResponse(
                teacher.getId(),
                teacher.getUser().getId(),
                teacher.getUser().getEmail(),
                teacher.getName(),
                teacher.getPhone()
        );
    }

    public TeacherResponse update(Long id, TeacherUpdateRequest request){

        Long schoolId = currentUserService.getCurrentSchoolId();

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: "+ id));

        if (!teacher.getUser().getSchool().getId().equals(schoolId)){
            throw new IllegalArgumentException("You can only update teachers of your own school");
        }

        teacher.setName(request.getName());
        teacher.setPhone(request.getPhone());

        Teacher savedTeacher = teacherRepository.save(teacher);

        return new TeacherResponse(
                savedTeacher.getId(),
                savedTeacher.getUser().getId(),
                savedTeacher.getUser().getEmail(),
                savedTeacher.getName(),
                savedTeacher.getPhone()
        );
    }

    public void delete(Long id){

        Long schoolId = currentUserService.getCurrentSchoolId();

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: "+ id));

        if (!teacher.getUser().getSchool().getId().equals(schoolId)){
            throw new IllegalArgumentException("You can only delete teacher from your own school");
        }

        User user = teacher.getUser();

        teacherRepository.deleteById(id);
        userRepository.delete(user);

    }

    public String changePassword(ChangePasswordRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Teacher teacher = teacherRepository.findByUser_Id(currentUser.getId())
                .orElseThrow(() ->
                        new TeacherNotFoundException(
                                "Teacher profile not found"));

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
