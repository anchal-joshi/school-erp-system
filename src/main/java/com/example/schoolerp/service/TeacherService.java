package com.example.schoolerp.service;

import com.example.schoolerp.dto.TeacherRequest;
import com.example.schoolerp.dto.TeacherResponse;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.repository.TeacherRepository;
import com.example.schoolerp.repository.UserRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository, CurrentUserService currentUserService, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.passwordEncoder = passwordEncoder;
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

}
