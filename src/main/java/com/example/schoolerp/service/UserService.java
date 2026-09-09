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

        User currentUser = currentUserService.getCurrentUser();

        if (currentUser.getRole() == UserRole.SUPER_ADMIN){
            if (request.getRole() != UserRole.SCHOOL_ADMIN){
                throw new IllegalArgumentException(
                        "Super Admin can only create School Admins"
                );
            }

            if (request.getSchoolId() == null){
                throw new IllegalArgumentException(
                        "School is required for School Admin"
                );
            }

        }

        if (currentUser.getRole() == UserRole.SCHOOL_ADMIN){
            if (request.getRole() != UserRole.TEACHER &&
                    request.getRole() != UserRole.STUDENT){
                throw new IllegalArgumentException(
                        "School Admin can only create Teachers or Students"
                );
            }

            if (request.getSchoolId() == null){
                throw new IllegalArgumentException(
                        "School is required"
                );
            }

            if (!currentUserService.getCurrentSchoolId()
                    .equals(request.getSchoolId())){
                throw new IllegalArgumentException(
                        "You can only create users for your own school"
                );
            }
        }

        School school = null;

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

        User currentUser = currentUserService.getCurrentUser();

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: "+ id
                        )
                );

        if (user.getRole() == UserRole.SUPER_ADMIN){
            throw new IllegalArgumentException(
                    "You cannot change the status of a Super Admin"
            );
        }

        if (currentUser.getRole() != UserRole.SUPER_ADMIN){

            if (!currentUserService.getCurrentSchoolId().equals(user.getSchool().getId())){
                throw new IllegalArgumentException("You cannot change status of users outside your school");
            }

        }

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

    public void deleteUser(Long id){

        User currentUser = currentUserService.getCurrentUser();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: "+ id));

        if (user.getRole() == UserRole.SUPER_ADMIN){
            throw new IllegalArgumentException("You cannot delete a super admin");
        }

        if (currentUser.getRole() == UserRole.SUPER_ADMIN){
            if (user.getRole() != UserRole.SCHOOL_ADMIN){
                throw new IllegalArgumentException("Super Admin can only delete school admins\"");
            }
        } else if (currentUser.getRole() == UserRole.SCHOOL_ADMIN){
            if (!currentUser.getSchool().getId().equals(user.getSchool().getId())){
                throw new IllegalArgumentException("You cannot delete users from other schools");
            }
            if (user.getRole() == UserRole.SCHOOL_ADMIN){
                throw new IllegalArgumentException("School Admin cannot delete another school admin");
            }
        }

        userRepository.deleteById(id);
    }


}
