package com.example.schoolerp.repository;

import com.example.schoolerp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
