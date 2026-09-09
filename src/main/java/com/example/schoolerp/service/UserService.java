package com.example.schoolerp.service;

import com.example.schoolerp.dto.SchoolResponse;
import com.example.schoolerp.dto.UserRequest;
import com.example.schoolerp.dto.UserResponse;
import com.example.schoolerp.entity.School;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.entity.UserRole;
import com.example.schoolerp.entity.UserStatus;
import com.example.schoolerp.exception.SchoolNotFoundException;
import com.example.schoolerp.exception.UserNotFoundException;
import com.example.schoolerp.repository.SchoolRepository;
import com.example.schoolerp.repository.UserRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserService currentUserService;

    public UserService(UserRepository userRepository, SchoolRepository schoolRepository, PasswordEncoder passwordEncoder, CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUserService = currentUserService;
    }

    public UserResponse createUser(UserRequest request){
        School school = null;

        if(request.getRole() != UserRole.SUPER_ADMIN
        && request.getSchoolId() == null){
            throw new IllegalArgumentException("School is required for this role");
        }

        if (request.getSchoolId() != null){
            school = schoolRepository.findById(request.getSchoolId())
                    .orElseThrow(() -> new SchoolNotFoundException("School not found"));
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setSchool(school);

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getStatus(),
                savedUser.getSchool() != null? savedUser.getSchool().getId() : null,
                savedUser.getCreatedAt()
        );
    }

    public UserResponse updateStatus(Long id, UserStatus status){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: "+ id
                        )
                );
        user.setStatus(status);
        User savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getStatus(),
                savedUser.getSchool() != null? savedUser.getSchool().getId() : null,
                savedUser.getCreatedAt()
        );
    }

    public List<UserResponse> getAllUsers(){
        Long schoolId = currentUserService.getCurrentSchoolId();
        List<User> users = userRepository.findBySchool_Id(schoolId);
        List<UserResponse> response = users
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getRole(),
                        user.getStatus(),
                        user.getSchool() != null? user.getSchool().getId() : null,
                        user.getCreatedAt())
                )
                .collect(Collectors.toList());
        return response;
    }


}
