package com.example.schoolerp.security;

import com.example.schoolerp.entity.SchoolStatus;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustomUserDetailsService service;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService service, UserRepository userRepository){
        this.jwtService = jwtService;
        this.service = service;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        String email = jwtService.extractEmail(jwt);

        UserDetails userDetails = service.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());


        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user.getSchool() !=null){
            if (user.getSchool().getStatus() == SchoolStatus.INACTIVE){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        SecurityContextHolder.getContext().setAuthentication(token);

        filterChain.doFilter(request, response);

    }


}
