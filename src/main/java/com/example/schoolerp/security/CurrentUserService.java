package com.example.schoolerp.security;

import com.example.schoolerp.entity.User;
import com.example.schoolerp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email);
    }

    public Long getCurrentSchoolId(){

        User user = getCurrentUser();
        if (user.getSchool() == null){
            return null;
        }

        return user.getSchool().getId();
    }



}
